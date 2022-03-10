package company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriterToFile {
    static FileWriter fileWriter;

    static {
        try {
            fileWriter = new FileWriter("src/company/simulation.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    public static void writeToFile(String msg) {
        try {
            bufferedWriter.write(msg);
            bufferedWriter.flush();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
