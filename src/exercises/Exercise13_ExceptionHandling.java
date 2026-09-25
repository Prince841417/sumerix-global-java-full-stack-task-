package exercises;
public class Exercise13_ExceptionHandling {
    static int divide(int a, int b) {
        if (b == 0) throw new IllegalArgumentException("Denominator cannot be zero.");
        return a / b;
    }
    public static void main(String[] args) {
        try {
            System.out.println(divide(10, 2));
            System.out.println(divide(10, 0));
        } catch (IllegalArgumentException e) {
            System.out.println("Handled exception: " + e.getMessage());
        } finally {
            System.out.println("Execution completed.");
        }
    }
}
