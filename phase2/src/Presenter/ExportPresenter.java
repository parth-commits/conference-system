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

    public void exportOption(){
        System.out.println("Do you want to export? (y/n)");
    }
    /**
     * The Export Presenter Class implements methods to export string as a text file.
     * @param info info of the conference
     */
    public void exportToFile(String info) throws IOException {
        try {
            OutputStream output = new FileOutputStream("phase2" + File.separator + "eventinfo.txt");
            output.write(info.getBytes(StandardCharsets.UTF_8));
            output.close();
        }catch(Exception IOException){
            System.out.println("Error export file.");
            throw new IOException();
        }
    }
}
