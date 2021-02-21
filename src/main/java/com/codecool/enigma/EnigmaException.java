package com.codecool.enigma;

public class EnigmaException extends Exception{

    public EnigmaException(String message) {
        super(message);
    }

    public EnigmaException(String message, Throwable cause) {
        super(message, cause);
    }

}
