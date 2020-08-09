package com;

import java.util.Scanner;

public class ReadabilityScoreStep1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        System.out.println((text.length() <= 100 ? "EASY" : "HARD"));
    }
}
