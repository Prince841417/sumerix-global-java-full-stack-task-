package exercises;
import java.util.ArrayList;
import java.util.Scanner;
public class Exercise06_StudentRecordManager {
    static class Student {
        String name; int age;
        Student(String name, int age) { this.name = name; this.age = age; }
        public String toString() { return name + " (" + age + ")"; }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("Rahul", 20));
        list.add(new Student("Aman", 21));
        list.forEach(System.out::println);
    }
}
