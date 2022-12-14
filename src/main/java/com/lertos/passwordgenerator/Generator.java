package com.lertos.passwordgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    private List<Integer> symbolAsciiCharacters;

    private Random rng;

    private boolean useLowercase;
    private boolean useUppercase;
    private boolean useNumbers;
    private boolean useSymbols;

    public Generator(boolean useLowercase, boolean useUppercase, boolean useNumbers, boolean useSymbols) {
        this.symbolAsciiCharacters = createSymbolSet();

        this.rng = new Random();

        this.useLowercase = useLowercase;
        this.useUppercase = useUppercase;
        this.useNumbers = useNumbers;
        this.useSymbols = useSymbols;
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

    public void setUseLowercase(boolean useLowercase) {
        this.useLowercase = useLowercase;
    }

    public void setUseUppercase(boolean useUppercase) {
        this.useUppercase = useUppercase;
    }

    public void setUseNumbers(boolean useNumbers) {
        this.useNumbers = useNumbers;
    }

    public void setUseSymbols(boolean useSymbols) {
        this.useSymbols = useSymbols;
    }

    public String generatePassword(int length) {
        StringBuilder newPassword = new StringBuilder();

        for (int i=0; i<length; i++) {
            newPassword.append(getRandomLetter(true));
        }

        return newPassword.toString();
    }

    private char getRandomLetter(boolean isLower) {
        int random = 0;

        if (isLower)
            rng.nextInt(97,123);
        else
            rng.nextInt(65,90);

        return (char) random;
    }

    private char getRandomNumber() {
        int random = rng.nextInt(48,58);

        return (char) random;
    }

    private char getRandomSymbol() {
        int random = rng.nextInt(0, symbolAsciiCharacters.size());
        int randomSymbolKey = symbolAsciiCharacters.get(random);

        return (char) randomSymbolKey;
    }

}
