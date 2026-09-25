package main;

import exception.InvalidStudentException;
import exception.StudentNotFoundException;
import model.Student;
import repository.FileStudentRepository;
import repository.StudentRepository;
import service.StudentService;
import util.InputUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String DATA_FILE = "data/students.txt";

    public static void main(String[] args) {
        StudentRepository repository = new FileStudentRepository(DATA_FILE);
        StudentService service = new StudentService(repository);
        InputUtil input = new InputUtil(new Scanner(System.in));

        System.out.println("==========================================");
        System.out.println("     STUDENT MANAGEMENT SYSTEM");
        System.out.println("==========================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = input.readInt("Enter choice: ");

            try {
                switch (choice) {
                    case 1 -> addStudent(input, service);
                    case 2 -> viewStudents(service);
                    case 3 -> updateStudent(input, service);
                    case 4 -> deleteStudent(input, service);
                    case 5 -> searchStudent(input, service);
                    case 6 -> {
                        service.save();
                        System.out.println("Data saved successfully.");
                    }
                    case 7 -> {
                        service.save();
                        System.out.println("Thank you for using Student Management System.");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice. Select 1-7.");
                }
            } catch (InvalidStudentException | StudentNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("""

                1. Add Student
                2. View All Students
                3. Update Student
                4. Delete Student
                5. Search Student
                6. Save Records
                7. Exit
                """);
    }

    private static void addStudent(InputUtil input, StudentService service)
            throws InvalidStudentException {
        int id = input.readInt("Student ID: ");
        String name = input.readNonEmpty("Name: ");
        int age = input.readInt("Age: ");
        String course = input.readNonEmpty("Course: ");
        double marks = input.readDouble("Marks (0-100): ");

        service.addStudent(new Student(id, name, age, course, marks));
        System.out.println("Student added successfully.");
    }

    private static void viewStudents(StudentService service) {
        List<Student> students = service.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("------------- STUDENTS -------------");
        students.forEach(System.out::println);
    }

    private static void updateStudent(InputUtil input, StudentService service)
            throws InvalidStudentException, StudentNotFoundException {
        int id = input.readInt("Enter student ID to update: ");
        String name = input.readNonEmpty("New name: ");
        int age = input.readInt("New age: ");
        String course = input.readNonEmpty("New course: ");
        double marks = input.readDouble("New marks (0-100): ");

        service.updateStudent(new Student(id, name, age, course, marks));
        System.out.println("Student updated successfully.");
    }

    private static void deleteStudent(InputUtil input, StudentService service)
            throws StudentNotFoundException {
        int id = input.readInt("Enter student ID to delete: ");
        service.deleteStudent(id);
        System.out.println("Student deleted successfully.");
    }

    private static void searchStudent(InputUtil input, StudentService service) {
        String name = input.readNonEmpty("Enter name to search: ");
        List<Student> results = service.searchStudents(name);
        if (results.isEmpty()) {
            System.out.println("No matching students found.");
            return;
        }
        results.forEach(System.out::println);
    }
}
