package FileHandlersFactoryPattern;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.apache.logging.log4j.LogManager;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class XMLHandler extends FileHandler {

    public XMLHandler(File _file) {
        file = _file;
        result = new ArrayList<>();
    }


    @XmlRootElement(name = "operations")
    private static class XMLDeserialization {

        @XmlElementWrapper(name = "addition")
        @XmlElement(name = "expression")
        private List<String> addition = new ArrayList<>();

        @XmlElementWrapper(name = "subtraction")
        @XmlElement(name = "expression")
        private List<String> subtraction = new ArrayList<>();

        @XmlElementWrapper(name = "multiplication")
        @XmlElement(name = "expression")
        private List<String> multiplication = new ArrayList<>();

        @XmlElementWrapper(name = "division")
        @XmlElement(name = "expression")
        private List<String> division = new ArrayList<>();

        @XmlElementWrapper(name = "combination")
        @XmlElement(name = "expression")
        private List<String> combination = new ArrayList<>();

        private List<List<String>> allOperations = new ArrayList<>();

        private List<List<String>> getAllOperations() {
            return allOperations;
        }

        private void mergeAllOperations() {
            allOperations.add(addition);
            allOperations.add(subtraction);
            allOperations.add(multiplication);
            allOperations.add(division);
            allOperations.add(combination);
        }
    }

    @Override
    public void fileProcessing() throws FileNotFoundException, JAXBException {
        LogManager.getLogger(XMLHandler.class).info("XML file processing has started");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String body = reader.lines().collect(Collectors.joining());
        StringReader stringReader = new StringReader(body);
        JAXBContext context = JAXBContext.newInstance(XMLDeserialization.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        XMLDeserialization deserialization = (XMLDeserialization) unmarshaller.unmarshal(stringReader);

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
            }else{
                LogManager.getLogger(XMLHandler.class).debug("Ariеhmetic operation's list is empty");
            }

        }
        LogManager.getLogger(XMLHandler.class).info("XML file processing has finished");
    }


}
