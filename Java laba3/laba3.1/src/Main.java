import java.io.*;
import java.util.Objects;
import java.util.StringTokenizer;

//Сироткин Илья 5 группа
// Задание 11 Условие:
//Строки текстового файла input.txt состоят из слов, разделенных одним или несколькими пробелами.
// Перед первым, а также после последнего слова строки пробелы могут отсутствовать.
// Требуется найти слова максимальной и минимальной длины и поменять местами строки, содержащие эти слова.
// Если таких слов несколько – брать первые.
public class Main {
    public static void main(String[] args) {
try(BufferedReader fin=new BufferedReader(new FileReader("input.txt"))){
    if(!fin.ready()) {
        System.out.println("Файл не открыт");
        return;
    }
StringBuilder min=new StringBuilder();
StringBuilder max=new StringBuilder();
    int lengthOfMaxToken=0;
    int lengthOfMinToken=Integer.MAX_VALUE;
String inputString=fin.readLine();
while(inputString!=null){
    lengthOfMaxToken=0;
    lengthOfMinToken=Integer.MAX_VALUE;
    StringTokenizer token=new StringTokenizer(inputString, " ");
    while(token.hasMoreTokens()){
        String currentWord=token.nextToken();
        if(currentWord.length()>lengthOfMaxToken){
            max= new StringBuilder(currentWord);
            lengthOfMaxToken=currentWord.length();
        }else if(currentWord.length()<lengthOfMinToken){
            min= new StringBuilder(currentWord);
            lengthOfMinToken=currentWord.length();
        }
    }
    System.out.println("Изначальная строка:\n "+inputString+'\n'+"Преобразованная строка: ");
    String[] words=inputString.split(" ");
    boolean isMaxReplaced=false;
    boolean isMinReplaced=false;
    for(String el:words){
        if(el.matches(max.toString())&&!isMaxReplaced){
            System.out.print(min.toString()+" ");
            isMaxReplaced=true;
        }else if(el.matches(min.toString())&&!isMinReplaced) {
            System.out.print(max.toString()+" ");
            isMinReplaced=true;
        }else {
            System.out.print(el+" ");
        }
    }
    System.out.println();
    System.out.println();
    inputString=fin.readLine();
}
}catch(IOException e){
    throw new RuntimeException(e);
}

    }
}