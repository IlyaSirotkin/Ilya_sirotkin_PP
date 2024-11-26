package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader fin = new BufferedReader(new InputStreamReader(System.in))) {
            String inputString = fin.readLine();

            ArrayList<Integer> array = Factorial.factorialCalculation(Integer.parseInt(inputString));
            array.forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}