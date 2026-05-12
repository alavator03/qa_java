package com.zoology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatTest {

    @Mock
    private Feline mockFeline;

    private Cat cat;

    @BeforeEach
    void setUp() {
        cat = new Cat(mockFeline);
    }

    @Test
    void getSound_ShouldReturnMeow() {
        // Act
        String sound = cat.getSound();

        // Assert
        assertEquals("Мяу", sound);
    }

    @Test
    void getFood_ShouldCallFelineGetFoodWithPredator() throws Exception {
        // Arrange
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(mockFeline.getFood("Хищник")).thenReturn(expectedFood);

        // Act
        List<String> actualFood = cat.getFood();

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
            cat.getFood();
        });

        assertEquals("Ошибка в Feline", exception.getMessage());
        verify(mockFeline, times(1)).getFood("Хищник");
    }

    @Test
    void getKittens_ShouldCallFelineGetKittens() {
        // Arrange
        when(mockFeline.getKittens()).thenReturn(3);

        // Act
        int kittens = cat.getKittens();

        // Assert
        assertEquals(3, kittens);
        verify(mockFeline, times(1)).getKittens();
    }
}