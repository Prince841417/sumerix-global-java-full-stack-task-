package exercises;
import java.io.*;
public class Exercise12_FileDatabase {
    public static void main(String[] args) throws IOException {
        File file = new File("data/exercise12.txt");
        try (BufferedWriter w = new BufferedWriter(new FileWriter(file))) {
            w.write("101|Prince|B.Tech CSE");
        }
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            System.out.println("Stored record: " + r.readLine());
        }
    }
}
