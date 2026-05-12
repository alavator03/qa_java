package com.zoology;

import java.util.List;

public class Lion {

    private final Feline feline;
    private final String sex;

    public Lion(String sex, Feline feline) throws Exception {
        this.feline = feline;
        if (sex.equals("Самец") || sex.equals("Самка")) {
            this.sex = sex;
        } else {
            throw new Exception("Используйте допустимые значения пола: Самец или Самка");
        }
    }

    public String getFamily() {
        return feline.getFamily();
    }

    public List<String> getFood() throws Exception {
        return feline.getFood("Хищник");
    }

    public int getKittens() {
        return feline.getKittens();
    }

    public String getSex() {
        return sex;
    }

    public boolean doesHaveMane() {
        return sex.equals("Самец");
    }
}