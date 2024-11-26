package org.example;

import java.util.ArrayList;

public class Factorial {
    private static ArrayList<Integer> array=new ArrayList<>();

    public static ArrayList<Integer> factorialCalculation(Integer value) {
        array.clear();
        if(value>16){
            array.add(-1);
        }else {
            array.add(1);
            for (int i = 1; i <= value; ++i) {
                array.add(array.getLast() * i);
            }
        }
        return array;
    }

}
