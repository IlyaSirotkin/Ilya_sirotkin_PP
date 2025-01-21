package FileOutputsFactoryPattern;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TextOutput extends FileOutput {
    public TextOutput(ArrayList<Double> array){
        result = array;
    }

    @Override
    public void fileFlushing() throws IOException {

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(new File("output/output.txt")))) {
            for(double value : result){
                fileWriter.write(String.valueOf(value)+"\n");
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
