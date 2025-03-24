package com.example;

import java.util.Arrays;

public class Array {
    public static void main(String[] args) {
        int[] array = new int[5];
        array[0] = 1;
        array[1] = 14;
        array[3] = array[1];
        array[4] = 5;

        System.out.println(Arrays.toString(array));
    }
}
