package com.example;

public class Loops {
    public static void main(String[] args) {
        int n = 1;
        while (n < 100) {
            System.out.println(n);
            n = 2 * n;
        }
        // n = 128
        System.out.println("After loop");

        for (int i = 0; i < 11; i = i + 1) {
            System.out.println(i);
        }
        // i = 11
    }
}
