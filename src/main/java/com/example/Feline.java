package com.zoology;

import java.util.List;
import java.util.Arrays;

public class Feline {

    public String getFamily() {
        return "Кошачьи";
    }

    public List<String> getFood(String animalKind) throws Exception {
        if (animalKind.equals("Хищник")) {
            return List.of("Животные", "Птицы", "Рыба");
        } else if (animalKind.equals("Травоядное")) {
            return List.of("Трава", "Листья", "Фрукты");
        } else {
            throw new Exception("Некорректный тип животного");
        }
    }

    public int getKittens() {
        return 1;
    }

    public int getKittens(int kittensCount) {
        return kittensCount;
    }
}