package FileHandlersFactoryPattern;

import java.io.File;

public class TextHandler extends FileHandler{
    private TextHandler(){}
    public TextHandler(File _file){
        initializeFile(_file);
    }

    @Override
    public void fileProcessing(){
        System.out.println("TEXT processing");
    }
}
