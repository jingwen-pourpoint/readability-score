package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ReadabilityScoreStep3 {
    public static void main(String[] args) {
        String text = null;
        int characters = 0;
        double score = 0;
        String absolutePathToFile = "C:/Users/jingw/Downloads/test.txt";
        File file = new File(absolutePathToFile);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                text = scanner.nextLine();
            }
            System.out.println(text);
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + absolutePathToFile);
        }

        String[] words = text.split(" ");
        System.out.println("Words: " + words.length);

        String[] sentences = text.split("[.!?]");
        System.out.println("Sentences: " + sentences.length);

        for (String word : words) {
            characters += word.split("").length;
        }
        System.out.println("Characters: " + characters);

        score = 4.71 * characters / words.length + 0.5 * words.length / sentences.length - 21.43;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("The score is: " + df.format(score));


        score = Math.ceil(score);
        switch ((int) score) {
            case 1:
                System.out.println("This text should be understood by 5-6 year olds.");
                break;
            case 2:
                System.out.println("This text should be understood by 6-7 year olds.");
                break;
            case 3:
                System.out.println("This text should be understood by 7-9 year olds.");
                break;
            case 4:
                System.out.println("This text should be understood by 9-10 year olds.");
                break;
            case 5:
                System.out.println("This text should be understood by 10-11 year olds.");
                break;
            case 6:
                System.out.println("This text should be understood by 11-12 year olds.");
                break;
            case 7:
                System.out.println("This text should be understood by 12-13 year olds.");
                break;
            case 8:
                System.out.println("This text should be understood by 13-14 year olds.");
                break;
            case 9:
                System.out.println("This text should be understood by 14-15 year olds.");
                break;
            case 10:
                System.out.println("This text should be understood by 15-16 year olds.");
                break;
            case 11:
                System.out.println("This text should be understood by 16-17 year olds.");
                break;
            case 12:
                System.out.println("This text should be understood by 17-18 year olds.");
                break;
            case 13:
                System.out.println("This text should be understood by 18-24 year olds.");
                break;
            case 14:
                System.out.println("This text should be understood by 24+ year olds.");
                break;
            default:
                System.out.println("not correct");
                break;

        }
    }
}

