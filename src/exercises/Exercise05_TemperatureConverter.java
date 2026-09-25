package exercises;
import java.util.Scanner;
public class Exercise05_TemperatureConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Celsius: ");
        double c = sc.nextDouble();
        double f = (c * 9 / 5) + 32;
        System.out.printf("Fahrenheit = %.2f%n", f);
    }
}
