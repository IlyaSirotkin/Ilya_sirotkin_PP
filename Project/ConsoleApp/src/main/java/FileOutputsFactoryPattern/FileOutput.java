package FileOutputsFactoryPattern;

import javax.xml.bind.JAXBException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class FileOutput {
    protected ArrayList<Double> result;

    public abstract void fileFlushing() throws IOException, JAXBException;
}
