package by.stormnet.utils;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class CharsGenerator {

    public static String generateRandomChars() {
        byte[] array = new byte[10];
        new Random().nextBytes(array);
        String generatedString;
        generatedString = new String(array, StandardCharsets.UTF_8);
        return generatedString;
    }
}
