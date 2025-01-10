package FileHandlersFactoryPattern;

import java.io.File;

public class JSONHandler extends FileHandler{
    private JSONHandler(){}
    public JSONHandler(File _file){
        initializeFile(_file);
    }

    @Override
    public void fileProcessing(){
        System.out.println("JSON processing");
    }
}
