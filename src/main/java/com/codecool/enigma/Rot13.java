package com.codecool.enigma;

class Rot13 implements Cipher {

    @Override
    public String encrypt(String message) {
        char[] messageInChars = message.toCharArray();
        for (int i = 0; i < message.length(); i++) {
            messageInChars[i] = encryptLetter(messageInChars[i]);
        }
        return String.valueOf(messageInChars);
    }

    @Override
    public String decrypt(String message) {
        char[] messageInChars = message.toCharArray();
        for (int i = 0; i < message.length(); i++) {
            messageInChars[i] = decryptLetter(messageInChars[i]);
        }
        return String.valueOf(messageInChars);
    }

    private char encryptLetter(char letter) {

        String alphabet = "abcdefghijklmnopqrstuvwxyz" + "abcdefghijklmnopqrstuvwxyz".toUpperCase();
        int newPosAfterShift = alphabet.indexOf(letter) + 13;
        if (newPosAfterShift == 12) return letter;
        if (alphabet.length() <= newPosAfterShift) {
            int indexOfShiftedChar = newPosAfterShift - alphabet.length();
            letter = String.valueOf(alphabet.charAt(indexOfShiftedChar)).toUpperCase().toCharArray()[0];
        } else {
            letter = String.valueOf(alphabet.charAt(newPosAfterShift)).toCharArray()[0];
        }
        return letter;
    }

    private char decryptLetter(char letter) {

        String alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase() + "abcdefghijklmnopqrstuvwxyz";
        int newPosAfterShift = alphabet.indexOf(letter) - 13;
        if (newPosAfterShift == -14) return letter;
        if (newPosAfterShift < 0) {
            int indexOfShiftedChar = newPosAfterShift + alphabet.length();
            letter = String.valueOf(alphabet.charAt(indexOfShiftedChar)).toLowerCase().toCharArray()[0];
        } else {
            letter = String.valueOf(alphabet.charAt(newPosAfterShift)).toCharArray()[0];
        }
        return letter;
    }

}
