package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        System.out.println("The text is:");
        String text = "";

        try {
            text = new String(Files.readAllBytes(Paths.get(args[0])));
        } catch (IOException e) {
            System.out.println("Cannot read file");
        }

        String[] sentences = text.split("[.?!]\\s+?");

        Scanner scanner = new Scanner(System.in);

        int wordCounter = 0;
        int characterCounter = 0;
        double score = 0;
        double fk = 0;
        double smog = 0;
        double cl = 0;
        String ageLevelARI = "";
        String ageLevelFK = "";
        String ageLevelSMOG = "";
        String ageLevelCL = "";
        float promAgeLevel = 0f;
        final int MINIMUM_AGE = 5;
        int syllables = 0;
        int polysyllables = 0;

        for (int i = 0; i < sentences.length; i++) {
            String[] wordsInSentence = sentences[i].split("\\s+");
            for (int j = 0; j < wordsInSentence.length; j++) {
                characterCounter += wordsInSentence[j].length();
                Pattern vowel = Pattern.compile("[aeiouy]");
                Matcher vowelMatcher = vowel.matcher(wordsInSentence[j].toLowerCase());
                Pattern doubleVowel = Pattern.compile("[aeiouy][aeiouy]");
                Matcher doubleVowelMatcher = doubleVowel.matcher(wordsInSentence[j].toLowerCase());
                int tempNumOfSyllables = 0;
                if (!wordsInSentence[j].matches(".*e")) {
                    while (vowelMatcher.find()) {
                        syllables++;
                        tempNumOfSyllables++;
                    }
                    int k = 0;
                    while (doubleVowelMatcher.find(k)) {
                        syllables--;
                        tempNumOfSyllables--;
                        k = doubleVowelMatcher.start() + 1;
                    }
                    if (tempNumOfSyllables > 2) {
                        polysyllables++;
                    }
                } else {
                    while (vowelMatcher.find()) {
                        tempNumOfSyllables++;
                    }
                    int k = 0;
                    while (doubleVowelMatcher.find(k)) {
                        tempNumOfSyllables--;
                        k = doubleVowelMatcher.start() + 1;
                    }
                    tempNumOfSyllables--;

                    if (tempNumOfSyllables > 2) {
                        polysyllables++;
                    }

                    if (tempNumOfSyllables == 0) {
                        syllables++;
                    } else {
                        syllables += tempNumOfSyllables;
                    }
                }
            }
            wordCounter += wordsInSentence.length;
        }
        // Let's add the number of punctuations that separate each sentence
        characterCounter += sentences.length - 1;

        score = Math.floor((4.71 * ((double) characterCounter / (double) wordCounter) +
                0.5 * ((double) wordCounter / (double) sentences.length) - 21.43) * 100) / 100;

        fk = 0.39 * (double) wordCounter / (double) sentences.length +
                11.8 * (double) syllables / (double) wordCounter - 15.59;

        smog = 1.043 * Math.sqrt((double) polysyllables * 30 / (double) sentences.length) + 3.1291;
        cl = 0.0588 * ((double) characterCounter / (double) wordCounter) * 100
                - 0.296 * ((double) sentences.length / (double) wordCounter) * 100 - 15.8;

        ageLevelARI = Integer.toString(MINIMUM_AGE + (int) Math.ceil(score));
        ageLevelFK = Integer.toString(MINIMUM_AGE + (int) Math.ceil(fk));
        ageLevelSMOG = Integer.toString(MINIMUM_AGE + (int) Math.ceil(smog));
        ageLevelCL = Integer.toString(MINIMUM_AGE + (int) Math.ceil(cl));

        promAgeLevel = (Float.parseFloat(ageLevelARI) + Float.parseFloat(ageLevelFK) +
                Float.parseFloat(ageLevelSMOG) + Float.parseFloat(ageLevelCL)) / 4;

        System.out.printf("Words: %d\n", wordCounter);
        System.out.printf("Sentences: %d\n", sentences.length);
        System.out.printf("Characters: %d\n",characterCounter);
        System.out.printf("Syllables: %d\n", syllables);
        System.out.printf("Polysyllables: %d\n", polysyllables);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
        System.out.println();
        String input = scanner.next();

        switch(input) {
            case "ARI":
                System.out.printf("Automated Readability Index: %.2f (about %s-year-olds).\n",
                                    score, ageLevelARI);
                break;
            case "FK":
                System.out.printf("Flesch–Kincaid readability tests: %.2f (about %s-year-olds).\n",
                        fk, ageLevelFK);
                break;
            case "SMOG":
                System.out.printf("Simple Measure of Gobbledygook: %.2f (about %s-year-olds).\n",
                        smog, ageLevelSMOG);
                break;
            case "CL":
                System.out.printf("Coleman–Liau index:  %.2f (about %s-year-olds).\n",
                        cl, ageLevelCL);
                break;
            case "all":
                System.out.printf("Automated Readability Index: %.2f (about %s-year-olds).\n",
                        score, ageLevelARI);
                System.out.printf("Flesch–Kincaid readability tests: %.2f (about %s-year-olds).\n",
                        fk, ageLevelFK);
                System.out.printf("Simple Measure of Gobbledygook: %.2f (about %s-year-olds).\n",
                        smog, ageLevelSMOG);
                System.out.printf("Coleman–Liau index:  %.2f (about %s-year-olds).\n",
                        cl, ageLevelCL);
                break;
            default:
                break;
        }

        System.out.println();
        System.out.printf("This text should be understood by %.2f year-olds.\n", promAgeLevel);
    }
}
