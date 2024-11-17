import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Сироткин Илья 5 группа. Задание 11 условие:
// Задана строка, слова в которой состоят из букв латинского алфавита и десятичных цифр.
// Остальные символы – разделители между словами. Получить новую строку, выполняя в заданной строке замены по следующему правилу.
// Все слова, имеющие длину менее 5 символов и состоящие только из цифр,
// заменяются словами, получаемыми из исходных путём дописывания ведущих нулей до получения длины слова,
// равной 5. Слова в исходной строке разделяются некоторым множеством разделителей.
// Слова в новой строке должны разделяться ровно одним пробелом.
public class Main1 {
    public static void main(String[] args) {
        try(BufferedReader cin=new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Введите строку: ");
            String inputString=cin.readLine();
            System.out.println(inputString);
            String[] words=inputString.split("\\s+|\\p{P}+|[а-яА-я]+|\\W+");

            System.out.println();
            int i=1;
            for(String el:words){
                StringBuilder str=new StringBuilder(el);
                if(str.length()>0) {
                    if(str.length()<5&&str.toString().matches("\\d+")){
                        while(str.length()<5){
                            str=new StringBuilder("0").append(str);
                        }
                    }
                    System.out.println(i++ + ") " + str);
                }
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}