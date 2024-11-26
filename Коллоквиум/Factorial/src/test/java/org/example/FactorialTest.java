package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {
    @Test
    void factorialTestFor5() {
        ArrayList<Integer> array = Factorial.factorialCalculation(5);
        ArrayList<Integer> arrayToCompare = new ArrayList<>();
        arrayToCompare.add(1);
        arrayToCompare.add(1);
        arrayToCompare.add(2);
        arrayToCompare.add(6);
        arrayToCompare.add(24);
        arrayToCompare.add(120);
        assertEquals(arrayToCompare, array);
    }

    @Test
    void factorialTestFor0() {
        ArrayList<Integer> array = Factorial.factorialCalculation(0);
        ArrayList<Integer> arrayToCompare = new ArrayList<>();
        arrayToCompare.add(1);
        assertEquals( arrayToCompare, array);
    }

    @Test
    void factorialTestFor17() {
        ArrayList<Integer> array = Factorial.factorialCalculation(17);
        ArrayList<Integer> arrayToCompare = new ArrayList<>();
        arrayToCompare.add(-1);
        assertEquals(array, arrayToCompare);
    }

}