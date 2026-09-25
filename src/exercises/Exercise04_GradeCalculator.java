package exercises;
import java.util.Scanner;
public class Exercise04_GradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Marks (0-100): ");
        double marks = sc.nextDouble();
        if (marks < 0 || marks > 100) System.out.println("Invalid marks.");
        else if (marks >= 90) System.out.println("Grade A");
        else if (marks >= 80) System.out.println("Grade B");
        else if (marks >= 70) System.out.println("Grade C");
        else if (marks >= 60) System.out.println("Grade D");
        else System.out.println("Grade F");
    }
}
