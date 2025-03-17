package com.example;

public class Expressions {
    public static void main(String[] args) {
        int a = 4;
        // a = 4.5; // Error! Incompatible types

        int x = 5;
        int y = 3;
        double z = 4.5;
        System.out.println(x + z - x / y + a * (x % y));

        int v = 3;
        double w = Math.pow(v, 2);
    }
}
