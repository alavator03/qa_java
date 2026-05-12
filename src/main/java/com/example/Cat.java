package com.zoology;

import java.util.List;

public class Cat {

    private final Feline feline;

    public Cat(Feline feline) {
        this.feline = feline;
    }

    public String getSound() {
        return "Мяу";
    }

    public List<String> getFood() throws Exception {
        return feline.getFood("Хищник");
    }

    public int getKittens() {
        return feline.getKittens();
    }
}