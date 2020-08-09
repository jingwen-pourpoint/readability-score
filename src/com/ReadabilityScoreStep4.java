package com;


import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ReadabilityScoreStep4 {

    static String text = "";
    static int nbWords = 0;
    static int nbSentences = 0;
    static int characters = 0;
    static int syllables = 0;
    static int polysyllables = 0;

    public static void main(String[] args) {
        readFile(args[0]);

        String[] words = text.split(" ");
        nbWords = words.length;

        String[] sentences = text.split("[.!?]");
        nbSentences = sentences.length;

        analyzeText(words);

        System.out.println("Words: " + nbWords);
        System.out.println("Sentences: " + nbSentences);
        System.out.println("Characters: " + characters);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllables);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String choice = scanner.nextLine();

        System.out.println();

        switch (choice) {
            case "ARI":
                calculate(computeAutomatedReadiblityIndex(), "Automated Readability Index: ");
                break;
            case "FK":
                calculate(computeFleshKincaidScore(),"Flesch–Kincaid readability tests: ");
                break;
            case "SMOG":
                calculate(computeSMOG(), "Simple Measure of Gobbledygook: ");
                break;
            case "CL":
                calculate(computeCL(), "Coleman–Liau index: ");
                break;
            case "all":
                double averageAge = calculateAll() / 4d;
                System.out.println("\nThis text should be understood in average by "
                        + new DecimalFormat("0.00").format(averageAge) + " year olds.");
                break;
        }

    }

    private static int calculateAll() {
        return calculate(computeAutomatedReadiblityIndex(), "Automated Readability Index: ")
                + calculate(computeFleshKincaidScore(),"Flesch–Kincaid readability tests: ")
                + calculate(computeSMOG(), "Simple Measure of Gobbledygook: ")
                + calculate(computeCL(), "Coleman–Liau index: ");
    }

    private static int calculate(double score, String methodName) {
        DecimalFormat df = new DecimalFormat("0.00");
        int ageToUnderstand = getAgeToUnderstand((int) Math.round(score));
        System.out.println(methodName + df.format(score) + " (about " + ageToUnderstand + " year olds).");
        return ageToUnderstand;
    }

    private static void readFile(String absolutePathToFile) {
        File file = new File(absolutePathToFile);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                text += scanner.nextLine();
            }
            System.out.println("The text is:");
            System.out.println(text);
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + absolutePathToFile);
        }
    }

    private static void analyzeText(String[] words) {
        for (String word : words) {
            String[] letters = word.split("");
            characters += letters.length;

            int nbVowel = analyzeLetters(letters);

            if (nbVowel == 0) {
                syllables++;
            } else {
                syllables += nbVowel;
            }

            if (nbVowel > 2) {
                polysyllables++;
            }
        }
    }

    private static int analyzeLetters(String[] letters) {
        String vowels = "aeiouyAEIOUY";
        int nbVowel = 0;
        for (int i = 0; i < letters.length - 1; i++) {
            String letter = letters[i];
            if (vowels.contains(letter) && !vowels.contains(letters[i + 1])) {
                nbVowel++;
            }
        }

        if (vowels.contains(letters[letters.length - 1]) && !"e".equals(letters[letters.length - 1])) {
            nbVowel++;
        }

        return nbVowel;
    }

    private static double computeAutomatedReadiblityIndex() {
        return 4.71 * (double) characters / nbWords + 0.5 * (double) nbWords / nbSentences - 21.43;
    }

    private static double computeFleshKincaidScore() {
        return .39 * (double) nbWords / nbSentences + 11.8 * (double) syllables / nbWords - 15.59;
    }

    private static double computeSMOG() {
        return 1.043 * Math.sqrt(polysyllables * 30d / nbSentences) + 3.1291;
    }

    private static double computeCL() {
        double s = (double) nbSentences / nbWords * 100;
        double l = (double) characters / nbWords * 100;

        return 0.0588 * l - 0.296 * s - 15.8;
    }

    private static int getAgeToUnderstand(int score) {
        if (score < 1) {
            score = 1;
        }
        switch (score) {
            case 1:
                return 6;
            case 2:
                return 7;
            case 3:
                return 9;
            case 4:
                return 10;
            case 5:
                return 11;
            case 6:
                return 12;
            case 7:
                return 13;
            case 8:
                return 14;
            case 9:
                return 15;
            case 10:
                return 16;
            case 11:
                return 17;
            case 12:
                return 18;
            case 13:
            case 14:
            default:
                return 24;
        }
    }
}
