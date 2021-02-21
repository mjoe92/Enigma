package com.codecool.enigma;

public class RailFence implements Cipher {

    @Override
    public String encrypt(String message) {
        int rails = Integer.parseInt(readOnlyMessageWithKey(message)[1]);
        message = readOnlyMessageWithKey(message)[0];

        char[] encrypted = new char[message.length()];
        int n = 0;

        for(int k = 0 ; k < rails; k ++) {
            int index = k;
            boolean down = true;
            while(index < message.length() ) {
                //System.out.println(k + " " + index+ " "+ n );
                encrypted[n++] = message.charAt(index);
                if(k == 0 || k == rails - 1) {
                    index = index + 2 * (rails - 1);
                } else if(down) {
                    index = index +  2 * (rails - k - 1);
                    down = false;
                } else {
                    index = index + 2 * k;
                    down = false;
                }
            }
        }
        return new String(encrypted);
    }

    @Override
    public String decrypt(String message) {
        int rails = Integer.parseInt(readOnlyMessageWithKey(message)[1]);
        message = readOnlyMessageWithKey(message)[0];

        char[] decrypted = new char[message.length()];
        int n = 0;

        for(int k = 0 ; k < rails; k ++) {
            int index = k;
            boolean down = true;
            while(index < message.length() ) {
                //System.out.println(k + " " + index+ " "+ n );
                decrypted[index] = message.charAt(n++);
                if(k == 0 || k == rails - 1) {
                    index = index + 2 * (rails - 1);
                }
                else if(down) {
                    index = index +  2 * (rails - k - 1);
                    down = false;
                }
                else {
                    index = index + 2 * k;
                    down = false;
                }
            }
        }
        return new String(decrypted);
    }

    public String[] readOnlyMessageWithKey(String message) {

        int j = message.length();
        for (int i = message.length() - 1; 0 <= i; i--) {
            if (message.charAt(i) == '_') break;
            j--;
        }
        return new String[]{message.substring(0, j - 1), message.substring(j)};
    }

}
