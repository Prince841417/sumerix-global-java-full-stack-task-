package exercises;
import java.util.HashMap;
public class Exercise10_InventoryConsole {
    public static void main(String[] args) {
        HashMap<String, Integer> inventory = new HashMap<>();
        inventory.put("Keyboard", 10);
        inventory.put("Mouse", 15);
        inventory.put("Monitor", 5);
        inventory.forEach((item, qty) -> System.out.println(item + " -> " + qty));
    }
}
