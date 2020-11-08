package by.stormnet.utils;

import java.util.Random;

public class CharsGenerator {

    public static String generateRandomCharsForContent() {
        String generatedStringForFileContent;
        String alphabetLine = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int length = 100;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabetLine.length());
            char randomChar = alphabetLine.charAt(index);
            stringBuilder.append(randomChar);
        }
        generatedStringForFileContent = stringBuilder.toString();
        return generatedStringForFileContent;
    }

    public static String generateRandomCharsForName() {
        String generatedStringForFileName;
        String alphabetLine = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int length = 3;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabetLine.length());
            char randomChar = alphabetLine.charAt(index);
            stringBuilder.append(randomChar);
        }
        generatedStringForFileName = stringBuilder.toString();
        return generatedStringForFileName;
    }
}

