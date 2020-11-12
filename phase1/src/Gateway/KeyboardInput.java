package Gateway;
import java.util.Scanner;

public class KeyboardInput {
    public String getKeyboardInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
        // system suggests inputString.nextLine()
    }
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        System.out.println(inputString);
    }
}