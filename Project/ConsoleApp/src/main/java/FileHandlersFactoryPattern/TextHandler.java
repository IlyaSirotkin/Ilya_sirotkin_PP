package FileHandlersFactoryPattern;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.apache.logging.log4j.LogManager;

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
        LogManager.getLogger(TextHandler.class).info("TEXT file processing has started");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        line = reader.readLine();
        Expression expression;
        double value=0;
        while(line!=null){
            if(line.matches(".+[+\\-*/].+")) {
                expression = new ExpressionBuilder(line).build();
                value = expression.evaluate();
                result.add(value);
                line = reader.readLine();
            }else{
                line = reader.readLine();
                if(line!=null) {
                    if (!line.matches(".+[+\\-*/].+")) {
                        LogManager.getLogger(TextHandler.class).debug("Ariеhmetic operation's list is empty");
                    }
                }
            }

        }
        LogManager.getLogger(TextHandler.class).info("TEXT file processing has finished");
    }
}
