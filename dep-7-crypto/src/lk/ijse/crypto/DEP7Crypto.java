package lk.ijse.crypto;

public class DEP7Crypto {
    /**
     * Get the plainText and the key to create an encrypted String.
     * Following steps are used for the encryption
     * Eg:  plainText = abcd
     * key = 123
     * output = üûúù
     *
     * @param plainText This is the text to be encrypted
     *                  It should be a String type
     * @param key       This it the encryption key
     *                  It should be a String
     * @return This method returns a String
     */
    public static String encrypt(String plainText, String key) {
        int plainTextLength = plainText.length();
        int keyLength = key.length();

        // Get the sum of key's char code values. Sum value is dependent on the char position of the key.
        int keyCharCodesSum = 0;
        for (int i = 0; i < keyLength; i++) {
            keyCharCodesSum += key.charAt(i) * i;
        }

        StringBuilder cipher = new StringBuilder();

        // key char sum value and original text char values are sum up to get the cypher
        for (int j = 0; j < plainTextLength; j++) {
            int textCharCode = plainText.charAt(j);
            int newCharCode = 0;

            if ((keyCharCodesSum + textCharCode) > (int) Character.MAX_VALUE) {
                newCharCode = keyCharCodesSum > textCharCode ? (keyCharCodesSum - textCharCode) : (textCharCode - keyCharCodesSum);
            } else {
                newCharCode = keyCharCodesSum + textCharCode;
            }

            cipher.append((char) newCharCode);

        }

        double plainTextRoot = Math.pow(plainTextLength, (double) 1 / 2);

        // Shuffle the cypher
        String suffledCyper = cipher.toString();
        for (int i = 0; i < (int) plainTextRoot; i++) {
            suffledCyper = stringShuffle(suffledCyper);
        }

        return suffledCyper;
    }

    /**
     * Get the cypherText and the key to create the decrypted String.
     * Following steps are used for the decryption
     * Eg:  cypherText = üûúù
     * key = 123
     * output = abcd
     *
     * @param cipherText This is the text to be decrypted
     *                   It should be a String type
     * @param key        This it the encryption key
     *                   It should be a String
     * @return This method returns a String
     */
    public static String decrypt(String cipherText, String key) {

        int cipherTextLength = cipherText.length();
        int keyLength = key.length();

        // Get the sum of key's char code values. Sum value is dependent on the char position of the key.
        int keyCharCodesSum = 0;
        for (int i = 0; i < keyLength; i++) {
            keyCharCodesSum += key.charAt(i) * i;
        }

        // Shuffle back the cypher
        String sufflebackedCipherText = cipherText;
        double cyperTextRoot = Math.pow(cipherTextLength, (double) 1 / 2);
        for (int i = 0; i < (int) cyperTextRoot; i++) {
            sufflebackedCipherText = stringShuffleback(sufflebackedCipherText);
        }

        // key char sum value and cypher text char values are sum up to get the original text
        StringBuilder plainText = new StringBuilder();
        for (int j = 0; j < cipherTextLength; j++) {
            int sufflebackedCipherTextCharCode = sufflebackedCipherText.charAt(j);
            int newCharCode = 0;

            if (((keyCharCodesSum * 2) + sufflebackedCipherTextCharCode) > (int) Character.MAX_VALUE) {
                newCharCode = sufflebackedCipherTextCharCode + keyCharCodesSum;
            } else {
                newCharCode = sufflebackedCipherTextCharCode - keyCharCodesSum;
            }

            plainText.append((char) newCharCode);
        }


        return plainText.toString();

    }

    /**
     * Get a String  and shuffle it as follow.
     * Eg:  string = abcdefgh
     * output = bdfhaceg
     *
     * @param string This is the text to be shuffle
     *               It should be a String type
     * @return This method returns a String
     */
    private static String stringShuffle(String string) {
        if (string.length() <= 1) {
            return string;
        }

        char[] chars = string.toCharArray();
        int charsLength = chars.length;

        int oddCharsCount = charsLength % 2 != 0 ? (charsLength / 2) + 1 : charsLength / 2;
        char[] oddChars = new char[oddCharsCount];

        int evenCharCount = charsLength / 2;
        char[] evenChars = new char[evenCharCount];

        for (int i = 0; i < charsLength; i++) {
            if (i % 2 == 0) {
                oddChars[i / 2] = chars[i];
            } else {
                evenChars[i / 2] = chars[i];
            }
        }

        int suffeledCharsLength = oddCharsCount + evenCharCount;
        char[] suffeledChars = new char[suffeledCharsLength];

        for (int i = 0; i < suffeledCharsLength; i++) {
            if (i < evenCharCount) {
                suffeledChars[i] = evenChars[i];
            } else {
                suffeledChars[i] = oddChars[i - evenCharCount];
            }
        }

        StringBuilder suffeledStringBuilder = new StringBuilder();

        for (int i = 0; i < suffeledCharsLength; i++) {
            suffeledStringBuilder.append(suffeledChars[i]);
        }

        return suffeledStringBuilder.toString();
    }

    /**
     * Get a String  and shuffle it back as follow.
     * Eg:  string = bdfhaceg
     * output = abcdefgh
     *
     * @param string This is the text to be shuffle back
     *               It should be a String type
     * @return This method returns a String
     */
    private static String stringShuffleback(String string) {
        if (string.length() <= 1) {
            return string;
        }

        char[] chars = string.toCharArray();
        int charsLength = chars.length;

        int firstCharSetLength = charsLength / 2;
        int secondCharSetLength = charsLength % 2 == 0 ? charsLength / 2 : (charsLength / 2) + 1;
        char[] firstCharSet = new char[firstCharSetLength];
        char[] secondCharSet = new char[secondCharSetLength];

        for (int i = 0; i < charsLength; i++) {
            if (i < firstCharSetLength) {
                firstCharSet[i] = chars[i];
            } else {
                secondCharSet[i - firstCharSetLength] = chars[i];
            }
        }

        int suffeledbackCharsLength = firstCharSetLength + secondCharSetLength;
        char[] suffeledbackChars = new char[suffeledbackCharsLength];

        for (int i = 0; i < suffeledbackCharsLength; i++) {
            if (i % 2 == 0) {
                suffeledbackChars[i] = secondCharSet[i / 2];
            } else {
                suffeledbackChars[i] = firstCharSet[i / 2];
            }
        }

        StringBuilder suffeledBackStringBuilder = new StringBuilder();

        for (int i = 0; i < suffeledbackCharsLength; i++) {
            suffeledBackStringBuilder.append(suffeledbackChars[i]);
        }

        return suffeledBackStringBuilder.toString();
    }
}
