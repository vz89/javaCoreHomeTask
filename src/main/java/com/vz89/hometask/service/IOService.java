package main.java.com.vz89.hometask.service;

import java.util.Scanner;

public class IOService {
    private static Scanner sc = new Scanner(System.in);

    public static void write(String text) {
        System.out.println(text);
    }

    public static String read() {
        return sc.next();
    }

    public static Long readLong() {
        return sc.nextLong();
    }
}
