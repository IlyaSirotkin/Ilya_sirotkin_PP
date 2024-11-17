import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//Сироткин Илья 5 группа Лаба 4.2. Условие:

//Входной файл input1.html содержит текст, написанный на языке HTML.
//В тесте находятся теги. В одной строке может быть несколько тегов. Теги находятся в угловых скобках,
//каждому открывающему тегу ставится в соответствие закрывающий тег. Например, пара тегов<b></b>.
//Входной файл input2.in содержит список фрагментов текста, которые нужно найти в первом файле,
//записанных через разделители (точка с запятой). Может быть несколько строк.

//Выходные данные:
//1.В выходной файл output1.out вывести список всех тегов в порядке возрастания количества символов тега.
//2.В выходной файл output2.out вывести номера строк (нумерация с 0) первого файла,
// в которых был найден искомый контекст в первый раз или -1 , если не был найден.
//3.В выходной файл output3.out - список фрагментов второго файла, которые НЕ были найдены в первом файле.
public class Main {

    public static void main(String[] args) {
        try (BufferedReader fin1 = new BufferedReader(new FileReader("input1.html"));
             BufferedReader fin2 = new BufferedReader(new FileReader("input2.in"))) {

            PrintStream fout1 = new PrintStream("output1.out");
            PrintStream fout2 = new PrintStream("output2.out");
            PrintStream fout3 = new PrintStream("output3.out");
            ArrayList<String> wordsToFind = new ArrayList<>();
            ArrayList<String> tagList = new ArrayList<>();

            String inputStringtoSearch = fin2.readLine();
            while (inputStringtoSearch != null) {
                String[] buf = inputStringtoSearch.split(";");
                for (String el : buf) {
                    wordsToFind.add(el);
                }
                inputStringtoSearch = fin2.readLine();
            }

            String inputStringOfTags = fin1.readLine();
            Pattern tagPattern = Pattern.compile("<[^<^>]+>",Pattern.CASE_INSENSITIVE);
            Pattern wordPattern;
            int indexOfLine=1;
            while (inputStringOfTags != null) {

                for(int i=0;i<wordsToFind.size();i++) {
                    wordPattern = Pattern.compile(wordsToFind.get(i).trim(), Pattern.CASE_INSENSITIVE);
                    Matcher wordMatcher = wordPattern.matcher(inputStringOfTags);
                    if(wordMatcher.find()){
                        fout2.println("Найдено слово "+wordsToFind.get(i)+" в строке "+indexOfLine);
                        wordsToFind.remove(i);
                    }
                }
                Matcher tagMatcher = tagPattern.matcher(inputStringOfTags);
                while (tagMatcher.find()) {
                    int start = tagMatcher.start();
                    int end = tagMatcher.end();
                    tagList.add(inputStringOfTags.substring(start, end));
                }
                inputStringOfTags = fin1.readLine();
                indexOfLine++;
            }

            Comparator<String> comprator = (o1, o2) -> o1.length() - o2.length();
            Collections.sort(tagList, comprator);

            fout1.println("Теги: ");
            for (int i = 0; i < tagList.size(); i++) {
                fout1.println(tagList.get(i));
            }
    fout3.println("Слова не были найдены среди тегов: ");
            for (int i = 0; i < wordsToFind.size(); i++) {
                fout3.println(wordsToFind.get(i));
            }
            System.out.println("Информация записана в файлы ");
        } catch (FileNotFoundException e) {
            System.out.println("Проверьте открываемый файл");
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}

