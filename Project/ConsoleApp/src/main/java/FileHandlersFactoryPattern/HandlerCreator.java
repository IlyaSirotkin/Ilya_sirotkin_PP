package FileHandlersFactoryPattern;

import java.io.File;

public interface HandlerCreator {

    FileHandler createXMLHandler(File file);
    FileHandler createTextHandler(File file);
    FileHandler createYAMLHandler(File file);
    FileHandler createJSONHandler(File file);

}
