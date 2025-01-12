package FileHandlersFactoryPattern;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.io.*;
import java.util.ArrayList;

public class TextHandler extends FileHandler{
    private TextHandler(){}
    public TextHandler(File _file) {
        file=_file;
        result = new ArrayList<>();
    }

    @Override
    public void fileProcessing() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        line = reader.readLine();
        Expression expression=null;
        double value=0;
        while(line!=null){
            expression = new ExpressionBuilder(line).build();
            value = expression.evaluate();
            result.add(value);
            line = reader.readLine();
        }
    }
}
