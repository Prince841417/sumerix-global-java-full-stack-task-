package service;

import model.Student;
import repository.StudentRepository;
import exception.InvalidStudentException;
import exception.StudentNotFoundException;

import java.util.List;

public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(Student student) throws InvalidStudentException {
        validate(student);
        try {
            repository.findById(student.getId());
            throw new InvalidStudentException("Student ID already exists.");
        } catch (StudentNotFoundException ignored) {
            repository.add(student);
        }
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student getStudentById(int id) throws StudentNotFoundException {
        return repository.findById(id);
    }

    public List<Student> searchStudents(String name) {
        return repository.searchByName(name);
    }

    public void updateStudent(Student student)
            throws InvalidStudentException, StudentNotFoundException {
        validate(student);
        repository.update(student);
    }

    public void deleteStudent(int id) throws StudentNotFoundException {
        repository.delete(id);
    }

    public void save() {
        repository.saveToFile();
    }

    private void validate(Student student) throws InvalidStudentException {
        if (student == null) throw new InvalidStudentException("Student cannot be null.");
        if (student.getId() <= 0) throw new InvalidStudentException("ID must be positive.");
        if (student.getName() == null || student.getName().isBlank())
            throw new InvalidStudentException("Name cannot be empty.");
        if (student.getAge() < 1 || student.getAge() > 100)
            throw new InvalidStudentException("Age must be between 1 and 100.");
        if (student.getCourse() == null || student.getCourse().isBlank())
            throw new InvalidStudentException("Course cannot be empty.");
        if (student.getMarks() < 0 || student.getMarks() > 100)
            throw new InvalidStudentException("Marks must be between 0 and 100.");
    }
}
