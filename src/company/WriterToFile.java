package company;

import java.io.FileWriter;

public class WriterToFile {
    public static void writeToFile(String msg) {
        try {
            FileWriter fileWriter = new FileWriter("simulation.txt");
            fileWriter.write(msg + "\n");
            fileWriter.close();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
