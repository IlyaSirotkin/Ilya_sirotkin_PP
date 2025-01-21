package FileOutputsFactoryPattern;

import java.util.ArrayList;

public class ConcreteOutputCreator implements OutputCreator {
    @Override
    public FileOutput createXMLOutput(ArrayList<Double> result) {
        return new XMLOutput(result);
    }

    @Override
    public FileOutput createTextOutput(ArrayList<Double> result) {
        return new TextOutput(result);
    }

    @Override
    public FileOutput createJSONOutput(ArrayList<Double> result) {
        return new JSONOutput(result);
    }
}
