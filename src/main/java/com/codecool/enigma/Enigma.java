package com.codecool.enigma;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Enigma {

    private static String MENU = "Enigma Manual\n" +
            "Run options: [-h | -e | -d] {CipherName} {FileName} {EncryptionKey}\n" +
            "   -h : displays this menu; other arguments are ignored.\n" +
            "   -e : encrypt and display\n" +
            "   -d : decrypt and display\n" +
            "   CipherName      : cipher to use when encrypting/decrypting; [rot13, rail-fence, morse]\n" +
            "   FileName        : path to file to encrypt/decrypt\n" +
            "   EncryptionKey   : Optional -> must be provided if cipher requires a key";

    public static void main(String[] args) {
        ArgsParser argsParser = new ArgsParser(args);
        String errorMessage = "";

        if (args.length == 0) {
            System.out.println("\n" + MENU + "\n");
        } else if (argsParser.getOption().equals("-h")) {
            System.out.println("\n" + MENU + "\n");
        } else {

            File codeFile = new File(argsParser.getFile());
            if (!argsParser.getOption().equals("-h")
                    && !argsParser.getOption().equals("-e")
                    && !argsParser.getOption().equals("-d")) {
                errorMessage = "Unacceptable option call! Options: [-h | -e | -d]";
            } else if (!argsParser.getOption().equals("-h") && argsParser.getKey() == null) {
                errorMessage = "Less arguments added! Type '-h' to see manual for help!";
            } else if (!codeFile.exists() && !codeFile.isFile()) {
                errorMessage = "Invalid filepath! Ensure you add a valid path for the file!";
            }

            if (!errorMessage.equals("")) {
                try {
                    throw new EnigmaException(errorMessage);
                } catch (EnigmaException e) {
                    e.printStackTrace();
                }
            } else {
                handleCipherOperation(argsParser);
            }
        }
    }

    private static void handleCipherOperation(ArgsParser argsParser) {

        try {
            Cipher cipher = CipherFactory.getCipherForArgs(argsParser);
            // use cipher
            File file = new File(argsParser.getFile());
            Scanner scanner = new Scanner(file);
            String content = scanner.nextLine();
            content = argsParser.getCipher().equals("rail-fence") ?
                    content + "_" + argsParser.getKey() : content;
            switch (argsParser.getOption()) {
                case "-e":
                    content = cipher.encrypt(content);
                    break;
                case "-d":
                    content = cipher.decrypt(content);
                    break;
            }
            System.out.println(content);

        } catch (EnigmaException | IOException eE) {
            eE.printStackTrace();
        }
    }

}
