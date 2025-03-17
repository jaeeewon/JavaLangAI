package com.example;

public class Methods {
    public static void main(String[] args) {
        System.out.println("The main method starts");
        String message = greetings(4, "Seoul");
        System.out.println(message);
        System.out.println("The main method ends");
    }

    public static String greetings(int times, String greetingTo) {
        System.out.println("Greetings starts");
        for (int i = 0; i < times; i++) {
            System.out.println("Hello " + greetingTo + "!");
        }
        System.out.println("Greetings ends");
        return "Greeted " + times + " times";
    }
}
