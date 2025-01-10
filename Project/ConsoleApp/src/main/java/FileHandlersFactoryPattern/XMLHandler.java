package FileHandlersFactoryPattern;

import java.io.File;

public class XMLHandler extends FileHandler {
    private XMLHandler(){}
    public XMLHandler(File _file){
        initializeFile(_file);
    }

    @Override
    public void fileProcessing(){
System.out.println("XML processing");
    }


}
