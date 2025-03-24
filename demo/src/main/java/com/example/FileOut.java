package com.example;

import java.io.*;

public class FileOut {
    public static void main(String[] args) throws FileNotFoundException {
        File outputFile = new File("out.txt");
        PrintStream output = new PrintStream(outputFile);
        output.print("Hello, world! ");
        output.println("#1 Bewe Moview fan!");
    }
}
