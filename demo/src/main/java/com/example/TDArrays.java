package com.example;

import java.util.Arrays;

public class TDArrays {
    public static void main(String[] args) {
        int[][] array = new int[5][5];
        array[1][2] = 1;
        array[1][3] = 14;
        array[3][4] = array[1][2];
        array[4][4] = 5;

        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
    }
}
