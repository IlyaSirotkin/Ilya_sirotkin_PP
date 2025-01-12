package FileHandlersFactoryPattern;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class YAMLHandler extends FileHandler {
    private YAMLHandler() {
    }

    public YAMLHandler(File _file) {
        file = _file;
    }

    private static class YAMLDeserialization {
        private List<String> addition = new ArrayList<>();
        private List<String> subtraction = new ArrayList<>();
        private List<String> multiplication = new ArrayList<>();
        private List<String> division = new ArrayList<>();
        private List<String> combination = new ArrayList<>();

        private List<List<String>> allOperations = new ArrayList<>();

        public List<List<String>> getAllOperations() {
            return allOperations;
        }
    }

    @Override
    public void fileProcessing() throws FileNotFoundException {
        //ObjectMapper om = new ObjectMapper(new YAMLFactory());

        //YAMLDeserialization deserialization = om.readValue(file, YAMLDeserialization.class);
       // deserialization.addition.forEach(System.out::println);
    }
}
