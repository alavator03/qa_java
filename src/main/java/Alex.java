package com.zoology;

import java.util.List;

public class Alex extends Lion {

    private static final String DEFAULT_SEX = "Самец";

    public Alex(Feline feline) throws Exception {
        super(DEFAULT_SEX, feline);
    }

    public List<String> getFriends() {
        return List.of("Марти", "Глория", "Мелман");
    }

    public String getPlaceOfLiving() {
        return "Нью-Йоркский зоопарк";
    }

    @Override
    public int getKittens() {
        return 0;
    }
}