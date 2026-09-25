package exercises;
import java.util.Scanner;
public class Exercise02_Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("First number: "); double a = sc.nextDouble();
        System.out.print("Operator (+ - * /): "); char op = sc.next().charAt(0);
        System.out.print("Second number: "); double b = sc.nextDouble();
        switch (op) {
            case '+' -> System.out.println("Result = " + (a+b));
            case '-' -> System.out.println("Result = " + (a-b));
            case '*' -> System.out.println("Result = " + (a*b));
            case '/' -> System.out.println(b == 0 ? "Cannot divide by zero." : "Result = " + (a/b));
            default -> System.out.println("Invalid operator.");
        }
    }
}
