package com.example;

import java.util.Arrays;

public class LoopArrays {
    public static void main(String[] args) {
        int[] array = new int[5];

        array[0] = 5;
        array[1] = -2;
        array[2] = 15;
        array[3] = 8;
        array[4] = 3;

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        System.out.println(sum);
        plusOne(sum);
        System.out.println(sum);

        System.out.println(Arrays.toString(array));
        shift(array);
        System.out.println(Arrays.toString(array));
    }

    public static void plusOne(int x) {
        // call by value
        x += 1;
    }

    public static void shift(int[] numbers) {
        // call by reference
        if (numbers.length > 0) {
            int firstNumber = numbers[0];

            for (int i = 0; i < numbers.length - 1; i++) {
                numbers[i] = numbers[i + 1];
            }

            numbers[numbers.length - 1] = firstNumber;
        }
    }
}
