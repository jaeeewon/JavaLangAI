package com.example;

public class Casting {
    public static void main(String[] args) {
        int a = 5;
        double b = 3.5;
        double c = (double) a;
        int d = (int) b;
        System.out.println(c); // Prints 5.0
        System.out.println(d); // Prints 3
    }
}
