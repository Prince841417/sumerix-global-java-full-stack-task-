package exercises;
import java.util.Random;
import java.util.Scanner;
public class Exercise03_NumberGuessingGame {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        int secret = random.nextInt(100) + 1, guess;
        System.out.println("Guess a number from 1 to 100.");
        do {
            System.out.print("Your guess: ");
            guess = sc.nextInt();
            if (guess < secret) System.out.println("Too low.");
            else if (guess > secret) System.out.println("Too high.");
            else System.out.println("Correct!");
        } while (guess != secret);
    }
}
