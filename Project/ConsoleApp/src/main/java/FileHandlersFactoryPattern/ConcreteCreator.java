package FileHandlersFactoryPattern;

import java.io.File;

public class ConcreteCreator implements Creator {

    @Override
    public FileHandler createXMLHandler(File file){
        return new XMLHandler(file);
    }

    @Override
    public FileHandler createYAMLHandler(File file){
        return new YAMLHandler(file);
    }

    @Override
    public FileHandler createTextHandler(File file){
        return new TextHandler(file);
    }

    @Override
    public FileHandler createJSONHandler(File file){
        return new JSONHandler(file);
    }

}
