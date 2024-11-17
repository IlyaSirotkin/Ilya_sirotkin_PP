import java.io.*;

//Сироткин Илья 5 группа Лаба 4.3
//Условие:
//        Строку получать из текстового файла input.txt.
//        Результат работы методов записывать в выходной текстовый файл output.txt.
//        1. Из заданной строки исключить символы, расположенные внутри круглых скобок. Сами скобки тоже должны быть исключены.
//        2. Из заданной строки удалить из каждой группы идущих подряд цифр, в которой более двух цифр, все цифры, начиная с третьей.
//        3. Из заданной строки удалить из каждой группы идущих подряд цифр все незначащие нули.
public class Main {
    public static String firstFunc(String str) {
        str = str.replaceAll("\\([^\\(^\\)]+\\)", "");
        return str;
    }

    public static String secondFunc(String str) {
        str = str.replaceAll("([0-9][0-9])[0-9]+", "$1");
        return str;
    }

    public static String thirdFunc(String str) {
        str = str.replaceAll("\\D0+(?![1-9]\\D)", "");
        return str;
    }

    public static void main(String[] args) {
        try (BufferedReader fin = new BufferedReader(new FileReader("input.txt"))) {
            PrintStream fout = new PrintStream("output.txt");
            String inputString = fin.readLine();
            fout.println("Изначальна строка: " + inputString);
            fout.println("Строка без записей в скобках: " + firstFunc(inputString));
            fout.println("Строка без чисел с кол-вом цифр >=3: " + secondFunc(inputString));
            fout.println("Строка без незначащихся нулей: " + thirdFunc(inputString));

        } catch (FileNotFoundException e) {
            System.out.println("Проверьте открываемфй файл");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}