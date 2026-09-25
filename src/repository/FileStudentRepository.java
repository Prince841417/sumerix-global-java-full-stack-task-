package repository;

import model.Student;
import exception.StudentNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStudentRepository implements StudentRepository {
    private final List<Student> students = new ArrayList<>();
    private final File file;

    public FileStudentRepository(String filePath) {
        this.file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        loadFromFile();
    }

    @Override
    public void add(Student student) {
        students.add(student);
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    @Override
    public Student findById(int id) throws StudentNotFoundException {
        for (Student student : students) {
            if (student.getId() == id) return student;
        }
        throw new StudentNotFoundException("Student with ID " + id + " not found.");
    }

    @Override
    public List<Student> searchByName(String name) {
        List<Student> result = new ArrayList<>();
        String query = name.toLowerCase();
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(query)) {
                result.add(student);
            }
        }
        return result;
    }

    @Override
    public void update(Student updated) throws StudentNotFoundException {
        Student existing = findById(updated.getId());
        existing.setName(updated.getName());
        existing.setAge(updated.getAge());
        existing.setCourse(updated.getCourse());
        existing.setMarks(updated.getMarks());
    }

    @Override
    public void delete(int id) throws StudentNotFoundException {
        Student student = findById(id);
        students.remove(student);
    }

    @Override
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Student student : students) {
                writer.write(student.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Could not save student data: " + e.getMessage());
        }
    }

    @Override
    public void loadFromFile() {
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                try {
                    students.add(Student.fromFileString(line));
                } catch (RuntimeException e) {
                    System.err.println("Skipping invalid record: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Could not load student data: " + e.getMessage());
        }
    }
}
