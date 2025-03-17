package com.example;

public class Conditionals {
    public static void main(String[] args) {
        int n = 4; // n: 4
        // if 괄호 안에 들어가는 수식 -> 결과값 boolean
        if (n % 3 == 0) { // false
            System.out.println("Case 0");
        } else if (n % 3 == 1 || n % 2 == 0) { // true
            System.out.println("Case 1"); // prints
        } else {
            System.out.println("Case 2");
        }
        System.out.println("After if/else");
    }
}
