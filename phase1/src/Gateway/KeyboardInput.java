package Gateway;
import java.util.Scanner;

public class KeyboardInput {
    public String getKeyboardInput(String[] args){
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        return inputString;
    }
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        System.out.println(inputString);
    }
}