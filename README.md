# Student Management System – Java Full Stack Internship Task 1

A Java Full Stack Student Management System developed as part of the **Java Full Stack Development Internship – Task 1**.

The project demonstrates Core Java, Object-Oriented Programming, collections, file handling, exception handling, input validation, and a web-based interface using HTML, CSS, and JavaScript.

---

## 📌 Project Overview

The **Student Management System** allows users to manage student records through a simple and professional web interface.

The application supports essential student management operations such as:

- Add Student
- View Student Records
- Search Student
- Update Student
- Delete Student
- Save Student Records
- Load Student Records
- Input Validation
- Exception Handling
- File-based Data Persistence

The project follows a modular Java package structure to keep the code clean, readable, and maintainable.

---

## ✨ Features

### 👨‍🎓 Student Management

- Add a new student
- View all student records
- Search students by name
- Update existing student information
- Delete student records
- Display student details

### 💾 File Handling

- Save student records to a file
- Load student records from a file
- Persistent student data using file-based storage

### 🛡️ Validation & Exception Handling

- Input validation for student information
- Custom exception handling
- Student-not-found handling
- Invalid student data handling

### 🌐 Web Interface

- Professional dashboard
- Student records table
- Add Student form
- Search functionality
- Edit and Delete actions
- Save Records functionality
- Clean HTML/CSS interface
- JavaScript-based frontend interaction

---

## 🛠️ Technologies Used

- **Java 21 LTS**
- **Core Java**
- **Object-Oriented Programming (OOP)**
- **ArrayList / Collections**
- **FileReader**
- **FileWriter**
- **BufferedReader**
- **BufferedWriter**
- **Custom Exceptions**
- **HTML5**
- **CSS3**
- **JavaScript**
- **Maven**
- **Git**
- **GitHub**

---

## 🏗️ Project Architecture

The project is organized into separate packages according to their responsibilities.

```text
Java-Full-Stack-Task-1/
│
├── src/
│   ├── model/
│   │   └── Student.java
│   │
│   ├── service/
│   │   └── StudentService.java
│   │
│   ├── repository/
│   │   ├── StudentRepository.java
│   │   └── FileStudentRepository.java
│   │
│   ├── exception/
│   │   ├── InvalidStudentException.java
│   │   └── StudentNotFoundException.java
│   │
│   ├── util/
│   │   └── InputUtil.java
│   │
│   ├── main/
│   │   ├── Main.java
│   │   └── WebServer.java
│   │
│   └── exercises/
│       └── Practical exercises
│
├── data/
│   └── students.txt
│
├── docs/
│   └── PROJECT_DOCUMENTATION.md
│
├── web/
│   ├── index.html
│   ├── style.css
│   └── app.js
│
├── .gitignore
├── pom.xml
├── README.md
└── SUBMISSION_CHECKLIST.txt
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
