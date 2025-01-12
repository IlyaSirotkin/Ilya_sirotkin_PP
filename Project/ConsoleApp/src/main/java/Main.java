import FileHandlersFactoryPattern.ConcreteCreator;
import FileHandlersFactoryPattern.Creator;
import FileHandlersFactoryPattern.FileHandler;
import org.json.simple.parser.ParseException;

import javax.swing.JFileChooser;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;


public class Main {

    public static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    public static void main(String[] args) {

        int chosenOption = FileChooser.instance().showDialog(null, "Открыть файл");
        File file = null;
        if (chosenOption == JFileChooser.APPROVE_OPTION) {
            file = FileChooser.instance().getSelectedFile();
            System.out.println(file.getName());
        } else {
            System.out.println("File isn't chosen");
        }
        Creator creator = new ConcreteCreator();
        FileHandler fileHandler = null;
        String str = getFileExtension(file);
        switch (str) {
            case "xml": {
                fileHandler = creator.createXMLHandler(file);
                break;
            }
            case "json": {
                fileHandler = creator.createJSONHandler(file);
                break;
            }
            case "txt": {
                fileHandler = creator.createTextHandler(file);
                break;
            }
            case "yml": {
                fileHandler = creator.createYAMLHandler(file);
                break;
            }
            default: {
                System.out.println("File extention isn't supported");
                break;
            }
        }
        if (fileHandler != null) {
            try {
                fileHandler.fileProcessing();
                fileHandler.getResult().forEach(System.out::println);
            } catch (IOException e) {
                    throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
