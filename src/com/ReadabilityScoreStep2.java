package com;

import java.util.Scanner;

public class ReadabilityScoreStep2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        int sum = 0;

        String[] sentences = text.split("[.!?]");

        for (String sentence : sentences) {
            sum += sentence.split(" ").length;
        }

        if (sum / sentences.length <= 10) {
            System.out.println("EASY");
        } else {
            System.out.println("HARD");
        }
    }
}

