package main;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import exception.InvalidStudentException;
import exception.StudentNotFoundException;
import model.Student;
import repository.FileStudentRepository;
import repository.StudentRepository;
import service.StudentService;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WebServer {
    private static final int PORT = 8080;
    private static final StudentService service =
            new StudentService(new FileStudentRepository("data/students.txt"));

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/", WebServer::serveStatic);
        server.createContext("/api/students", WebServer::students);
        server.createContext("/api/save", WebServer::save);
        server.setExecutor(null);
        server.start();
        System.out.println("StudentHub running at http://localhost:" + PORT);
        System.out.println("Press Ctrl+C to stop.");
    }

    private static void serveStatic(HttpExchange ex) throws IOException {
        String path = ex.getRequestURI().getPath();
        if (path.equals("/")) path = "/index.html";
        Path file = Path.of("web", path.substring(1)).normalize();
        if (!file.startsWith(Path.of("web")) || !Files.exists(file) || Files.isDirectory(file)) {
            send(ex, 404, "text/plain", "Not found");
            return;
        }
        String type = path.endsWith(".html") ? "text/html" : path.endsWith(".css") ? "text/css" : "application/javascript";
        byte[] data = Files.readAllBytes(file);
        ex.getResponseHeaders().set("Content-Type", type + "; charset=UTF-8");
        ex.sendResponseHeaders(200, data.length);
        try (OutputStream out = ex.getResponseBody()) { out.write(data); }
    }

    private static void students(HttpExchange ex) throws IOException {
        String method = ex.getRequestMethod();
        String path = ex.getRequestURI().getPath();
        try {
            if ("GET".equals(method)) {
                StringBuilder json = new StringBuilder("[");
                List<Student> list = service.getAllStudents();
                for (int i=0;i<list.size();i++) {
                    if(i>0) json.append(',');
                    json.append(toJson(list.get(i)));
                }
                json.append(']');
                send(ex,200,"application/json",json.toString());
            } else if ("POST".equals(method)) {
                Student s = fromJson(readBody(ex));
                service.addStudent(s); service.save();
                send(ex,201,"application/json",toJson(s));
            } else if (path.matches("/api/students/\\d+")) {
                int id = Integer.parseInt(path.substring(path.lastIndexOf('/')+1));
                if ("PUT".equals(method)) {
                    Student s = fromJson(readBody(ex)); s.setId(id);
                    service.updateStudent(s); service.save();
                    send(ex,200,"application/json",toJson(s));
                } else if ("DELETE".equals(method)) {
                    service.deleteStudent(id); service.save();
                    send(ex,200,"application/json","{\"message\":\"deleted\"}");
                } else send(ex,405,"application/json","{\"error\":\"Method not allowed\"}");
            } else send(ex,405,"application/json","{\"error\":\"Method not allowed\"}");
        } catch (InvalidStudentException | StudentNotFoundException | IllegalArgumentException e) {
            send(ex,400,"application/json","{\"error\":\""+escape(e.getMessage())+"\"}");
        }
    }

    private static void save(HttpExchange ex) throws IOException {
        service.save();
        send(ex,200,"application/json","{\"message\":\"saved\"}");
    }

    private static String readBody(HttpExchange ex) throws IOException {
        try(InputStream in=ex.getRequestBody()) { return new String(in.readAllBytes(), StandardCharsets.UTF_8); }
    }

    private static Student fromJson(String j) {
        return new Student(
            intValue(j,"id"), stringValue(j,"name"), intValue(j,"age"),
            stringValue(j,"course"), doubleValue(j,"marks")
        );
    }
    private static int intValue(String j,String k){return Integer.parseInt(raw(j,k));}
    private static double doubleValue(String j,String k){return Double.parseDouble(raw(j,k));}
    private static String stringValue(String j,String k){return raw(j,k).replace("\\\"","\"");}
    private static String raw(String j,String k){
        String key="\""+k+"\":";
        int i=j.indexOf(key); if(i<0)throw new IllegalArgumentException("Missing field: "+k);
        int s=i+key.length();
        while(s<j.length()&&Character.isWhitespace(j.charAt(s)))s++;
        if(j.charAt(s)=='"'){int e=s+1;while(e<j.length()){if(j.charAt(e)=='"'&&j.charAt(e-1)!='\\')break;e++;}return j.substring(s+1,e);}
        int e=s;while(e<j.length()&&",}".indexOf(j.charAt(e))<0)e++;
        return j.substring(s,e).trim();
    }
    private static String toJson(Student s){
        return "{\"id\":"+s.getId()+",\"name\":\""+escape(s.getName())+"\",\"age\":"+s.getAge()+
               ",\"course\":\""+escape(s.getCourse())+"\",\"marks\":"+s.getMarks()+"}";
    }
    private static String escape(String s){return s==null?"":s.replace("\\","\\\\").replace("\"","\\\"");}
    private static void send(HttpExchange ex,int code,String type,String body)throws IOException{
        byte[] data=body.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type",type+"; charset=UTF-8");
        ex.sendResponseHeaders(code,data.length);
        try(OutputStream out=ex.getResponseBody()){out.write(data);}
    }
}
