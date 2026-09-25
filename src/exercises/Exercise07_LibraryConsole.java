package exercises;
import java.util.ArrayList;
public class Exercise07_LibraryConsole {
    public static void main(String[] args) {
        ArrayList<String> books = new ArrayList<>();
        books.add("Java Fundamentals"); books.add("Clean Code"); books.add("Spring Boot");
        System.out.println("Library Books:");
        books.forEach(System.out::println);
    }
}
