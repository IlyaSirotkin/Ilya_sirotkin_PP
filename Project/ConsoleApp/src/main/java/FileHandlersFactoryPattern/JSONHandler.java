package FileHandlersFactoryPattern;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONHandler extends FileHandler {

    private JSONParser parser;

    public JSONHandler(File _file) {
        file = _file;
        parser = new JSONParser();
        result = new ArrayList<>();
    }

    @Override
    public void fileProcessing() throws IOException, ParseException {

        Object obj = parser.parse(new FileReader(file));
        JSONObject jObj = (JSONObject) obj;

        ArrayList<JSONArray> operationsArray = new ArrayList<>();
        operationsArray.add((JSONArray) jObj.get("addition"));
        operationsArray.add((JSONArray) jObj.get("subtraction"));
        operationsArray.add((JSONArray) jObj.get("multiplication"));
        operationsArray.add((JSONArray) jObj.get("division"));
        operationsArray.add((JSONArray) jObj.get("combination"));

        Expression expression = null;

        double value = 0;
        for (int i = 0; i < operationsArray.size(); i++) {
            if (operationsArray.get(i) != null) {
                for (int j = 0; j < operationsArray.get(i).size(); j++) {
                    expression = new ExpressionBuilder((String) operationsArray.get(i).get(j)).build();
                    value = expression.evaluate();
                    result.add(value);
                }
            }
        }
    }
}
