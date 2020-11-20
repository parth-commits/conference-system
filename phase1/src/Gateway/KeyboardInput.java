package Gateway;
import java.util.Scanner;

public class KeyboardInput {
    public String getKeyboardInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
        // system suggests inputString.nextLine()
    }
}