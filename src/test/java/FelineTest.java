package com.zoology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FelineTest {

    private Feline feline;

    @BeforeEach
    void setUp() {
        feline = new Feline();
    }

    @Test
    void getFamilyShouldReturnCorrectFamily() {
        // Act
        String family = feline.getFamily();

        // Assert
        assertEquals("Кошачьи", family);
    }

    @ParameterizedTest
    @CsvSource({
            "Хищник, Животные;Птицы;Рыба",
            "Травоядное, Трава;Листья;Фрукты"
    })
    void getFoodWithValidAnimalKindShouldReturnCorrectFood(String animalKind, String expectedFoodList) throws Exception {
        // Act
        List<String> food = feline.getFood(animalKind);
        List<String> expected = List.of(expectedFoodList.split(";"));

        // Assert
        assertEquals(expected, food);
    }

    @Test
    void getFoodWithInvalidKindShouldThrowException() {
        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            feline.getFood("Неизвестное");
        });

        assertEquals("Некорректный тип животного", exception.getMessage());
    }

    @Test
    void getKittensWithoutArgumentsShouldReturnOne() {
        // Act
        int kittens = feline.getKittens();

        // Assert
        assertEquals(1, kittens);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 5, 10})
    void getKittensWithArgumentShouldReturnSpecifiedCount(int kittensCount) {
        // Act
        int kittens = feline.getKittens(kittensCount);

        // Assert
        assertEquals(kittensCount, kittens);
    }
}