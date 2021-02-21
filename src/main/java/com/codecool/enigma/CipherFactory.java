package com.codecool.enigma;

class CipherFactory {

    static boolean isCipherAvailable(String cipherName) {
        return cipherName.equals("morse")
                || cipherName.equals("rail-fence")
                || cipherName.equals("rot13");
    }

    static Cipher getCipherForArgs(ArgsParser args) throws EnigmaException {
        String cipherName = args.getCipher().toLowerCase();
        if (isCipherAvailable(cipherName)) {
            Cipher cipher = null;
            switch (cipherName) {
                case "morse":
                    cipher = new MorseCode();
                    break;
                case "rail-fence":
                    cipher = new RailFence();
                    break;
                case "rot13":
                    cipher = new Rot13();
                    break;
            }
            return cipher;
        } else {
            String message = "Unacceptable cipher call!";
            throw new EnigmaException(message);
        }
    }
}
