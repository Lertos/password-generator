package com.lertos.passwordgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

enum CharType {
    LOWERCASE,
    UPPERCASE,
    NUMBER,
    SYMBOL
}

public class Generator {

    private List<Integer> symbolAsciiCharacters;
    private List<CharType> listsToGenerateFrom;

    private Random rng;

    public Generator() {
        this.symbolAsciiCharacters = createSymbolSet();
        this.listsToGenerateFrom = new ArrayList<>();

        this.rng = new Random();
    }

    private List<Integer> createSymbolSet() {
        List<Integer> symbolList = new ArrayList<>();

        for (int i=33; i<48; i++)
            symbolList.add(i);
        for (int i=58; i<65; i++)
            symbolList.add(i);
        for (int i=91; i<97; i++)
            symbolList.add(i);
        for (int i=123; i<127; i++)
            symbolList.add(i);

        return symbolList;
    }

    public void updateCharTypeList(CharType type, boolean value) {
        if (value)
            listsToGenerateFrom.add(type);
        else
            listsToGenerateFrom.remove(type);
    }

    public String generatePassword(int length) {
        StringBuilder newPassword = new StringBuilder();
        int random;
        for (int i=0; i<length; i++) {
            random = rng.nextInt(0, 4);

            newPassword.append(Character.valueOf((char) getRandomLetter(true)));
        }

        return newPassword.toString();
    }

    private int getRandomLetter(boolean isLower) {
        int random = 0;

        if (isLower)
            random = rng.nextInt(97,123);
        else
            random = rng.nextInt(65,90);

        return random;
    }

    private int getRandomNumber() {
        int random = rng.nextInt(48,58);

        return random;
    }

    private int getRandomSymbol() {
        int random = rng.nextInt(0, symbolAsciiCharacters.size());
        int randomSymbolKey = symbolAsciiCharacters.get(random);

        return randomSymbolKey;
    }

}
