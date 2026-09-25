# Task 1 – Project Documentation

## 1. Project Title
Console-Based Student Management System

## 2. Objective
Build a modular console application in Core Java that demonstrates Java fundamentals, OOP, collections, exception handling, file handling, input validation, and Git/GitHub project organization.

## 3. Main Modules
### Model
`Student` stores student information and provides conversion to/from the file format.

### Repository
`StudentRepository` defines data operations. `FileStudentRepository` stores records in a text file and uses `ArrayList` for in-memory management.

### Service
`StudentService` contains validation and application rules.

### Utility
`InputUtil` handles safe integer, decimal, and non-empty string input.

### Exception
Custom exceptions provide clear error handling for invalid records and missing students.

### Main
`Main` provides the console menu and connects the modules.

## 4. OOP Principles Used
- Encapsulation: Student fields are private and accessed through getters/setters.
- Abstraction: StudentRepository is an interface defining data operations.
- Polymorphism: FileStudentRepository is used through the StudentRepository interface.
- Separation of responsibilities: model, service, repository, utility, and exception packages.

## 5. Collections
`ArrayList<Student>` is used to manage the student records.

## 6. File Handling
`FileReader`/`BufferedReader` load records and `FileWriter`/`BufferedWriter` save records to `data/students.txt`.

## 7. Validation
- Student ID must be positive and unique.
- Name and course cannot be empty.
- Age must be between 1 and 100.
- Marks must be between 0 and 100.
- Numeric input is validated before conversion.

## 8. Exception Handling
Custom checked exceptions:
- `InvalidStudentException`
- `StudentNotFoundException`

`try-catch` is used for user-facing error handling and file operations.

## 9. Expected Console Operations
1. Add Student
2. View All Students
3. Update Student
4. Delete Student
5. Search Student
6. Save Records
7. Exit

## 10. Testing Checklist
- Add valid student.
- Try duplicate ID.
- Try invalid age.
- Try marks below 0 or above 100.
- View all records.
- Search by partial name.
- Update existing student.
- Update non-existing student.
- Delete existing student.
- Delete non-existing student.
- Restart application and confirm saved records load.

## 11. Submission Checklist
- Java source code
- Console application
- File handling module
- GitHub repository
- README.md
- Screenshots
- Project documentation
