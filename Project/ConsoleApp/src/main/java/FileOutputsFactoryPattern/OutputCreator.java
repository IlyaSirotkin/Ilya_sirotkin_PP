package FileOutputsFactoryPattern;

import java.util.ArrayList;

public interface OutputCreator {
    FileOutput createXMLOutput(ArrayList<Double> result);
    FileOutput createTextOutput(ArrayList<Double> result);
    FileOutput createJSONOutput(ArrayList<Double> result);
}
