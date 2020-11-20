package Gateway;
import java.util.Scanner;

/** KeyboardInput receives the alphabets that users enter.
 *  @author Group_0112
 *  @version 1.0
 *  @since November 19th, 2020
 */

public class KeyboardInput {

    /**
     * Return the alphabets that users enter to the program.
     * @return String The alphabets from users
     */
    public String getKeyboardInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
        // system suggests inputString.nextLine()
    }
}