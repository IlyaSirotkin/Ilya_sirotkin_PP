package FileHandlersFactoryPattern;

import org.json.simple.parser.ParseException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class FileHandler {
    protected File file;
    protected ArrayList<Double> result;

    public ArrayList<Double> getResult() {
        return result;
    }

    public abstract void fileProcessing() throws IOException, ParseException, JAXBException;
}
