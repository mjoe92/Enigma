package com.codecool.enigma;

class ArgsParser {

    private String option = null;
    private String cipher = null;
    private String file = null;
    private String key = null;

    ArgsParser(String[] args) {

        if (args.length == 0) {
            option = cipher = file = key = null;
        }
        try {
            option = args[0];
            cipher = args[1];
            file = args[2];
            key = args[3];
        } catch (ArrayIndexOutOfBoundsException ignored){}
    }

    public String getOption() {
        return option;
    }

    public String getCipher() {
        return cipher;
    }

    public String getFile() {
        return file;
    }

    public String getKey() {
        return key;
    }
}
