package com.zoology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LionTest {

    @Mock
    private Feline mockFeline;

    private Lion lionMale;
    private Lion lionFemale;

    @BeforeEach
    void setUp() throws Exception {
        lionMale = new Lion("Самец", mockFeline);
        lionFemale = new Lion("Самка", mockFeline);
    }

    // Тесты конструктора
    @Test
    void constructor_WithMaleSex_ShouldCreateLion() throws Exception {
        // Act & Assert
        assertDoesNotThrow(() -> new Lion("Самец", mockFeline));
    }

    @Test
    void constructor_WithFemaleSex_ShouldCreateLion() throws Exception {
        // Act & Assert
        assertDoesNotThrow(() -> new Lion("Самка", mockFeline));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Самец", "Самка"})
    void constructor_WithValidSex_ShouldNotThrowException(String sex) {
        // Act & Assert
        assertDoesNotThrow(() -> new Lion(sex, mockFeline));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Мужской", "Женский", "s", "", "   ", "Male", "Female"})
    void constructor_WithInvalidSex_ShouldThrowException(String invalidSex) {
        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new Lion(invalidSex, mockFeline);
        });

        assertEquals("Используйте допустимые значения пола: Самец или Самка", exception.getMessage());
    }

    // Тесты getFamily
    @Test
    void getFamily_ShouldReturnFamilyFromFeline() {
        // Arrange
        when(mockFeline.getFamily()).thenReturn("Кошачьи");

        // Act
        String family = lionMale.getFamily();

        // Assert
        assertEquals("Кошачьи", family);
        verify(mockFeline, times(1)).getFamily();
    }

    // Тесты getFood
    @Test
    void getFood_ShouldCallFelineGetFoodWithPredator() throws Exception {
        // Arrange
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(mockFeline.getFood("Хищник")).thenReturn(expectedFood);

        // Act
        List<String> actualFood = lionMale.getFood();

        // Assert
        assertEquals(expectedFood, actualFood);
        verify(mockFeline, times(1)).getFood("Хищник");
    }

    @Test
    void getFood_WhenFelineThrowsException_ShouldPropagateException() throws Exception {
        // Arrange
        when(mockFeline.getFood("Хищник")).thenThrow(new Exception("Ошибка в Feline"));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            lionMale.getFood();
        });

        assertEquals("Ошибка в Feline", exception.getMessage());
        verify(mockFeline, times(1)).getFood("Хищник");
    }

    // Тесты getKittens
    @Test
    void getKittens_ShouldCallFelineGetKittens() {
        // Arrange
        when(mockFeline.getKittens()).thenReturn(2);

        // Act
        int kittens = lionMale.getKittens();

        // Assert
        assertEquals(2, kittens);
        verify(mockFeline, times(1)).getKittens();
    }

    // Тесты getSex
    @Test
    void getSex_ForMaleLion_ShouldReturnMale() {
        // Act
        String sex = lionMale.getSex();

        // Assert
        assertEquals("Самец", sex);
    }

    @Test
    void getSex_ForFemaleLion_ShouldReturnFemale() {
        // Act
        String sex = lionFemale.getSex();

        // Assert
        assertEquals("Самка", sex);
    }

    // Параметризованный тест для doesHaveMane
    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    void doesHaveMane_ShouldReturnTrueForMaleFalseForFemale(String sex, boolean expected) throws Exception {
        // Arrange
        Lion lion = new Lion(sex, mockFeline);

        // Act
        boolean hasMane = lion.doesHaveMane();

        // Assert
        assertEquals(expected, hasMane);
    }
}