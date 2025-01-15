package FileHandlersFactoryPattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class YAMLHandler extends FileHandler {

    public YAMLHandler(File _file) {
        file = _file;
        result = new ArrayList<>();
    }

    private static class YAMLDeserialization {

        @JsonProperty("addition")
        private List<String> addition = new ArrayList<>();
        @JsonProperty("subtraction")
        private List<String> subtraction = new ArrayList<>();
        @JsonProperty("multiplication")
        private List<String> multiplication = new ArrayList<>();
        @JsonProperty("division")
        private List<String> division = new ArrayList<>();
        @JsonProperty("combination")
        private List<String> combination = new ArrayList<>();

        private List<List<String>> allOperations = new ArrayList<>();

        private void mergeAllOperations() {
            allOperations.add(addition);
            allOperations.add(subtraction);
            allOperations.add(multiplication);
            allOperations.add(division);
            allOperations.add(combination);
        }

        public List<List<String>> getAllOperations() {
            return allOperations;
        }
    }

    @Override
    public void fileProcessing() throws IOException {
        ObjectMapper om = new ObjectMapper(new YAMLFactory());

        YAMLDeserialization deserialization = om.readValue(file, YAMLDeserialization.class);
        deserialization.mergeAllOperations();

        Expression expression;
        double value = 0;

        for (int i = 0; i < deserialization.getAllOperations().size(); i++) {
            if (deserialization.getAllOperations().get(i) != null) {
                for (int j = 0; j < deserialization.getAllOperations().get(i).size(); j++) {
                    expression = new ExpressionBuilder(deserialization.getAllOperations().get(i).get(j)).build();
                    value = expression.evaluate();
                    result.add(value);
                }
            }
        }
    }
}
