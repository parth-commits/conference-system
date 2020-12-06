package Presenter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * The Export Presenter Class implements methods to export string as a text file.
 * @author Group_0112
 * @version 1.0
 * @since December 1st, 2020
 */
public class ExportPresenter {
    /**
     * The Export Presenter Class implements methods to export string as a text file.
     * @param info info of the conference
     */
    public void exportToFile(String info) throws IOException {
        OutputStream output = new FileOutputStream("phase2" + File.separator + "eventinfo.txt");
        output.write(info.getBytes(StandardCharsets.UTF_8));
        output.close();
    }
}
