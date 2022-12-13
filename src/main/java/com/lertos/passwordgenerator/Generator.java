package com.lertos.passwordgenerator;

import java.util.Random;

public class Generator {

    private Random rng;

    private String currentPassword;

    private boolean useLowercase;
    private boolean useUppercase;
    private boolean useNumbers;
    private boolean useSymbols;

    public Generator(boolean useLowercase, boolean useUppercase, boolean useNumbers, boolean useSymbols) {
        this.rng = new Random();

        this.currentPassword = "";

        this.useLowercase = useLowercase;
        this.useUppercase = useUppercase;
        this.useNumbers = useNumbers;
        this.useSymbols = useSymbols;
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

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String generatePassword() {
        //Generate random values (inclusive, exclusive)
        int random = rng.nextInt(0,1);
    }

}
