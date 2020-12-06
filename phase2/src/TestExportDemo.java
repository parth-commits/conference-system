import java.io.IOException;
import java.text.ParseException;

/**
 * The TestExportDemo class contains just one method that helps this program to create
 * a new TestExportSystem and run.
 * @author Group_0112
 * @version 2.0
 * @since December 6th, 2020y
 */
public class TestExportDemo {
    public static void main(String[] args) throws IOException {
        TestExportSystem exportSystem = new TestExportSystem();
        exportSystem.run();
    }
}