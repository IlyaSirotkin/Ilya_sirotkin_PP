package FileOutputsFactoryPattern;

import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONOutput extends FileOutput{
    public JSONOutput(ArrayList<Double> array){result=array;}

    @Override
    public void fileFlushing() throws IOException {
        JSONObject obj = new JSONObject();
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(new File("output/output.json")))) {
            for(int i=1;i<=result.size();++i){
                obj.put(("result "+String.valueOf(i)), result.get(i-1));
            }
            fileWriter.write(obj.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            throw new IOException(e);
        }

    }
}
