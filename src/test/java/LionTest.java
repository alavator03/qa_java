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
    void constructorWithMaleSexShouldCreateLion() throws Exception {
        // Act & Assert
        assertDoesNotThrow(() -> new Lion("Самец", mockFeline));
    }

    @Test
    void constructorWithFemaleSexShouldCreateLion() throws Exception {
        // Act & Assert
        assertDoesNotThrow(() -> new Lion("Самка", mockFeline));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Самец", "Самка"})
    void constructorWithValidSexShouldNotThrowException(String sex) {
        // Act & Assert
        assertDoesNotThrow(() -> new Lion(sex, mockFeline));
    }

    @Test
    void constructorWithInvalidSexShouldThrowException() {
        // Act & Assert
        assertThrows(Exception.class, () -> new Lion("Мужской", mockFeline));
    }

    @Test
    void constructorWithInvalidSexShouldThrowExceptionWithCorrectMessage() {
        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> new Lion("Мужской", mockFeline));
        assertEquals("Используйте допустимые значения пола: Самец или Самка", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Женский", "s", "", "   ", "Male", "Female"})
    void constructorWithVariousInvalidSexShouldThrowException(String invalidSex) {
        // Act & Assert
        assertThrows(Exception.class, () -> new Lion(invalidSex, mockFeline));
    }

    // Тесты getFamily
    @Test
    void getFamilyShouldReturnFamilyFromFeline() {
        // Arrange
        when(mockFeline.getFamily()).thenReturn("Кошачьи");

        // Act
        String family = lionMale.getFamily();

        // Assert
        assertEquals("Кошачьи", family);
    }

    @Test
    void getFamilyShouldCallFelineGetFamily() {
        // Act
        lionMale.getFamily();

        // Assert
        verify(mockFeline, times(1)).getFamily();
    }

    // Тесты getFood
    @Test
    void getFoodShouldReturnFoodFromFeline() throws Exception {
        // Arrange
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(mockFeline.getFood("Хищник")).thenReturn(expectedFood);

        // Act
        List<String> actualFood = lionMale.getFood();

        // Assert
        assertEquals(expectedFood, actualFood);
    }

    @Test
    void getFoodShouldCallFelineGetFoodWithPredator() throws Exception {
        // Act
        lionMale.getFood();

        // Assert
        verify(mockFeline, times(1)).getFood("Хищник");
    }

    @Test
    void getFoodWhenFelineThrowsExceptionShouldPropagateException() throws Exception {
        // Arrange
        when(mockFeline.getFood("Хищник")).thenThrow(new Exception("Ошибка в Feline"));

        // Act & Assert
        assertThrows(Exception.class, () -> lionMale.getFood());
    }

    @Test
    void getFoodWhenFelineThrowsExceptionShouldHaveCorrectMessage() throws Exception {
        // Arrange
        when(mockFeline.getFood("Хищник")).thenThrow(new Exception("Ошибка в Feline"));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> lionMale.getFood());
        assertEquals("Ошибка в Feline", exception.getMessage());
    }

    @Test
    void getFoodWhenFelineThrowsExceptionShouldVerifyCall() throws Exception {
        // Arrange
        when(mockFeline.getFood("Хищник")).thenThrow(new Exception("Ошибка в Feline"));

        // Act
        assertThrows(Exception.class, () -> lionMale.getFood());

        // Assert
        verify(mockFeline, times(1)).getFood("Хищник");
    }

    // Тесты getKittens
    @Test
    void getKittensShouldReturnValueFromFeline() {
        // Arrange
        when(mockFeline.getKittens()).thenReturn(2);

        // Act
        int kittens = lionMale.getKittens();

        // Assert
        assertEquals(2, kittens);
    }

    @Test
    void getKittensShouldCallFelineGetKittens() {
        // Act
        lionMale.getKittens();

        // Assert
        verify(mockFeline, times(1)).getKittens();
    }

    // Тесты getSex
    @Test
    void getSexForMaleLionShouldReturnMale() {
        // Act
        String sex = lionMale.getSex();

        // Assert
        assertEquals("Самец", sex);
    }

    @Test
    void getSexForFemaleLionShouldReturnFemale() {
        // Act
        String sex = lionFemale.getSex();

        // Assert
        assertEquals("Самка", sex);
    }

    // Тесты для doesHaveMane
    @Test
    void doesHaveManeForMaleLionShouldReturnTrue() throws Exception {
        // Arrange
        Lion lion = new Lion("Самец", mockFeline);

        // Act
        boolean hasMane = lion.doesHaveMane();

        // Assert
        assertTrue(hasMane);
    }

    @Test
    void doesHaveManeForFemaleLionShouldReturnFalse() throws Exception {
        // Arrange
        Lion lion = new Lion("Самка", mockFeline);

        // Act
        boolean hasMane = lion.doesHaveMane();

        // Assert
        assertFalse(hasMane);
    }
}