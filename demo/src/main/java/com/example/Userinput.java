package com.example;

import java.util.*;
import java.io.*;

public class Userinput {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("Example.txt");
        Scanner fileScan = new Scanner(f);

        while (fileScan.hasNextLine()) {
            System.out.print(fileScan.nextLine() + ", ");
        }
        numericScanning();
        userScan();
    }

    public static void userScan() {
        Scanner userScan = new Scanner(System.in);
        while (userScan.hasNext()) {
            String val = userScan.next();
            System.out.println(val);
        }
        // ctrl+z > end of
    }

    public static void numericScanning() throws FileNotFoundException {
        Scanner fileScan = new Scanner(new File("data.txt"));
        while (fileScan.hasNextLine()) {
            String line = fileScan.nextLine();
            Scanner lineScan = new Scanner(line);
            double min = lineScan.nextDouble();
            double max = min;
            while (lineScan.hasNextDouble()) {
                double nextNum = lineScan.nextDouble();
                min = Math.min(min, nextNum);
                max = Math.max(max, nextNum);
            }
            System.out.println("Max: " + max + ", Min: " + min);
        }
    }
}
