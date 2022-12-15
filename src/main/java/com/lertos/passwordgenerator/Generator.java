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

    private final List<Integer> symbolAsciiCharacters;
    private final List<CharType> listsToGenerateFrom;

    private final Random rng;

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
        if (listsToGenerateFrom.size() == 0)
            return null;

        StringBuilder newPassword = new StringBuilder();
        CharType random;
        int type;

        for (int i=0; i<length; i++) {
            random = listsToGenerateFrom.get(rng.nextInt(listsToGenerateFrom.size()));

            type = switch (random) {
                case LOWERCASE -> getRandomLetter(true);
                case UPPERCASE -> getRandomLetter(false);
                case NUMBER -> getRandomNumber();
                case SYMBOL -> getRandomSymbol();
            };

            newPassword.append(Character.valueOf((char) type));
        }

        return newPassword.toString();
    }

    private int getRandomLetter(boolean isLower) {
        int random;

        if (isLower)
            random = rng.nextInt(97,123);
        else
            random = rng.nextInt(65,90);

        return random;
    }

    private int getRandomNumber() {
        return rng.nextInt(48,58);
    }

    private int getRandomSymbol() {
        int random = rng.nextInt(0, symbolAsciiCharacters.size());

        return symbolAsciiCharacters.get(random);
    }

}
