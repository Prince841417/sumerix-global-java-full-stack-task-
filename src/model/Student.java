package model;

public class Student {
    private int id;
    private String name;
    private int age;
    private String course;
    private double marks;

    public Student(int id, String name, int age, String course, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = marks;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }

    @Override
    public String toString() {
        return String.format(
            "ID: %d | Name: %s | Age: %d | Course: %s | Marks: %.2f",
            id, name, age, course, marks
        );
    }

    public String toFileString() {
        return id + "|" + name.replace("|", " ") + "|" + age + "|" +
               course.replace("|", " ") + "|" + marks;
    }

    public static Student fromFileString(String line) {
        String[] parts = line.split("\\|", -1);
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid student record.");
        }
        return new Student(
            Integer.parseInt(parts[0]),
            parts[1],
            Integer.parseInt(parts[2]),
            parts[3],
            Double.parseDouble(parts[4])
        );
    }
}
