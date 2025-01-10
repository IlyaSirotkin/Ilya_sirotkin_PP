package FileHandlersFactoryPattern;

import java.io.File;

public abstract class FileHandler {
    private File file;
    private String result;
    protected void initializeFile(File _file){
        file=_file;
    }

    public String getResult() {
        return result;
    }

    public abstract void fileProcessing();
}
