package repository;

import model.Student;
import exception.StudentNotFoundException;
import java.util.List;

public interface StudentRepository {
    void add(Student student);
    List<Student> findAll();
    Student findById(int id) throws StudentNotFoundException;
    List<Student> searchByName(String name);
    void update(Student student) throws StudentNotFoundException;
    void delete(int id) throws StudentNotFoundException;
    void saveToFile();
    void loadFromFile();
}
