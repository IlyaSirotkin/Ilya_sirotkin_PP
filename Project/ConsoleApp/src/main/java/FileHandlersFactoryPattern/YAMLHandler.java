package FileHandlersFactoryPattern;

import java.io.File;

public class YAMLHandler extends FileHandler{
    private YAMLHandler(){}
    public YAMLHandler(File _file){
        initializeFile(_file);
    }

    @Override
    public void fileProcessing(){
        System.out.println("YAML processing");
    }
}
