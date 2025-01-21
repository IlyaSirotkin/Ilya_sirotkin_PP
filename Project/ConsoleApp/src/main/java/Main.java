import FileHandlersFactoryPattern.ConcreteHandlerCreator;
import FileHandlersFactoryPattern.HandlerCreator;
import FileHandlersFactoryPattern.FileHandler;
import FileOutputsFactoryPattern.ConcreteOutputCreator;
import FileOutputsFactoryPattern.FileOutput;
import FileOutputsFactoryPattern.OutputCreator;
import org.json.simple.parser.ParseException;
import org.apache.logging.log4j.LogManager;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;


public class Main {

    private static FileHandler createFileHandler(HandlerCreator handlerCreator, File file) {
        String extension = getFileExtension(file);
        return switch (extension) {
            case "xml" -> handlerCreator.createXMLHandler(file);
            case "json" -> handlerCreator.createJSONHandler(file);
            case "txt" -> handlerCreator.createTextHandler(file);
            case "yaml" -> handlerCreator.createYAMLHandler(file);
            default -> {
                System.out.println("File extension isn't supported");
                LogManager.getLogger(Main.class).warn("File extension isn't supported");
                yield null;
            }
        };
    }

    private static FileOutput createFileOutput(OutputCreator outputCreator, ArrayList<Double> result, int option) {

        return switch (option) {
            case 1 -> outputCreator.createTextOutput(result);
            case 2 -> outputCreator.createXMLOutput(result);
            case 3 -> outputCreator.createJSONOutput(result);
            default -> {
                System.out.println("Опция не найдена");
                LogManager.getLogger(Main.class).warn("Option isn't found");
                yield null;
            }
        };
    }

    public static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    public static void main(String[] args) {
        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

        LogManager.getLogger(Main.class).info("Programm has been launched");
            int chosenOption = FileChooser.instance().showDialog(null, "Открыть файл");
            File file = null;

            if (chosenOption == FileChooser.APPROVE_OPTION) {
                file = FileChooser.instance().getSelectedFile();
                System.out.println(file.getName());
                LogManager.getLogger(Main.class).info("File is chosen");
            } else {
                System.out.println("File isn't chosen");
                LogManager.getLogger(Main.class).warn("File isn't chosen");
                return;
            }

            HandlerCreator handlerCreator = new ConcreteHandlerCreator();
            FileHandler fileHandler = null;

            fileHandler = createFileHandler(handlerCreator, file);

            if (fileHandler != null) {
                try {
                    fileHandler.fileProcessing();
                    fileHandler.getResult().forEach(System.out::println);
                } catch (IOException e) {
                    LogManager.getLogger(Main.class).error("IOException was thrown");
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    LogManager.getLogger(Main.class).error("ParseException was thrown");
                    throw new RuntimeException(e);
                } catch (JAXBException e) {
                    LogManager.getLogger(Main.class).error("JAXBException was thrown");
                    throw new RuntimeException(e);
                }
            } else {
                LogManager.getLogger(Main.class).warn("FileHandler is null");
                return;
            }

            OutputCreator outputCreator = new ConcreteOutputCreator();
            FileOutput fileOutput = null;

            System.out.println("Файл обработан");
            System.out.println("Выберете в каком формате вывести результаты:\n1. Text\n2. XML\n3. JSON");
            int option;
            try {
                option = Integer.parseInt(consoleInput.readLine());
                while(option<1||option>3){
                    System.out.println("Введите число от 1 до 3");
                    option = Integer.parseInt(consoleInput.readLine());
                }
                fileOutput=createFileOutput(outputCreator, fileHandler.getResult(), option);
                fileOutput.fileFlushing();
            }catch(IOException e){
                throw new RuntimeException(e);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        LogManager.getLogger(Main.class).info("Programm has finished without any problems");

    }
}
