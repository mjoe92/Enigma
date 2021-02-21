package com.codecool.enigma;

import java.util.HashMap;
import java.util.Map;

class MorseCode implements Cipher {

    @Override
    public String encrypt(String message) {
        int j = 0;
        while (message.charAt(j) == ' ' && j < message.length()) {
            j++;
        }

        message = message.substring(j);
        String decodedResult = "";
        message = message.toLowerCase();
        //String[] splitMessage = message.split(" ");
        char[] splitMessage = message.toCharArray();
        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
                "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
                ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----"};
        //char[] arguments = "abcdefghijklmnopqrstuvwxyz1234567890.,?! ".toCharArray();
        char[] arguments = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        Map<String, String> morseCodes = new HashMap<>();
        //morseCodes.put("...---...", "sos");
        morseCodes.put(" ", " ");
        for (int i = 0; i < arguments.length; i++) {
            morseCodes.put(String.valueOf(arguments[i]), morse[i]);
        }
        for (char letter : splitMessage) {
            decodedResult += " " + morseCodes.get(String.valueOf(letter));
        }
        decodedResult = decodedResult.substring(1);
        return decodedResult;
    }

    @Override
    public String decrypt(String message) {
        int j = 0;
        while (message.charAt(j) == ' ' && j < message.length()) {
            j++;
        }
        message = message.substring(j);
        String decodedResult = "";
        message = message.replaceAll("   "," / ").toLowerCase();
        String[] splitMorseCodes = message.split(" ");
        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
                "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
                ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", ".-.-.-", "--..--", "..--..", "-.-.--", "/"};
        char[] arguments = "abcdefghijklmnopqrstuvwxyz1234567890.,?! ".toCharArray();
        Map<String, String> morseCodes = new HashMap<>();
        morseCodes.put("...---...", "sos");
        morseCodes.put("", " ");
        for (int i = 0; i < arguments.length; i++) {
            morseCodes.put(morse[i], String.valueOf(arguments[i]));
        }
        for (String code : splitMorseCodes) {
            decodedResult += morseCodes.get(code);
        }
        return decodedResult.toUpperCase();
    }
}
