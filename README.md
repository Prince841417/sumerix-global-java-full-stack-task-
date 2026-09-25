# Java Full Stack Development Internship – Task 1

## Console-Based Student Management System

This project implements the Task 1 mini/capstone project using Core Java, OOP, collections, exception handling, file handling, and Git/GitHub-ready project organization.

## Features
- Add Student
- Update Student
- Delete Student
- Search Student
- Display All Students
- Save Records to File
- Load Records from File
- Input Validation
- Exception Handling
- Modular Package Structure
- Clean, readable Java code

## Technologies
- Java 21 LTS
- Core Java
- OOP
- ArrayList
- FileReader / FileWriter
- BufferedReader / BufferedWriter
- Custom Exceptions
- Git & GitHub

## Project Structure
```text
Java-Full-Stack-Task-1/
├── src/
│   ├── model/
│   │   └── Student.java
│   ├── service/
│   │   └── StudentService.java
│   ├── repository/
│   │   ├── StudentRepository.java
│   │   └── FileStudentRepository.java
│   ├── exception/
│   │   ├── InvalidStudentException.java
│   │   └── StudentNotFoundException.java
│   ├── util/
│   │   └── InputUtil.java
│   └── main/
│       └── Main.java
├── data/
│   └── students.txt
├── docs/
│   └── PROJECT_DOCUMENTATION.md
├── README.md
├── .gitignore
└── pom.xml
```

## Run with JDK 21

### Compile
From the project root:
```bash
javac -d out src/model/*.java src/exception/*.java src/repository/*.java src/service/*.java src/util/*.java src/main/*.java
```

### Run
```bash
java -cp out main.Main
```

## Maven
```bash
mvn clean package
java -cp target/classes main.Main
```

## Example data format
Records are persisted in `data/students.txt` using:
```text
id|name|age|course|marks
```

## Git commands
```bash
git init
git add .
git commit -m "Complete Java Full Stack Internship Task 1"
git branch -M main
git remote add origin YOUR_GITHUB_REPOSITORY_URL
git push -u origin main
```

## Future Enhancements
- JDBC/MySQL database
- Maven/JUnit tests
- Spring Boot REST API
- React frontend
- Authentication and role-based access

## Practical Exercises
The `src/exercises/` folder contains implementations/examples for the Task 1 practical exercise list:
1. Pattern printing
2. Calculator
3. Number guessing game
4. Student grade calculator
5. Temperature converter
6. Student record manager
7. Library management console
8. Banking console application
9. Employee payroll calculator
10. Inventory management console
11. Student Management System (main project)
12. File-based database example
13. Exception handling example
14. Modular Java application notes
15. GitHub project submission steps


## Professional Web UI (HTML/CSS)
A professional responsive dashboard has been added in `web/`. It uses HTML/CSS/JavaScript and connects to the same Java service and `data/students.txt` through `WebServer.java`.

### Start the web version
Compile:
```bash
javac -d out src/model/*.java src/exception/*.java src/repository/*.java src/service/*.java src/util/*.java src/main/*.java
```

Run:
```bash
java -cp out main.WebServer
```

Open:
```text
http://localhost:8080
```

The web UI supports:
- Dashboard statistics
- Add student
- View/search students
- Update student
- Delete student
- Save records
- File persistence using the existing Java repository
