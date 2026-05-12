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
class AlexTest {

    @Mock
    private Feline mockFeline;

    private Alex alex;

    @BeforeEach
    void setUp() throws Exception {
        alex = new Alex(mockFeline);
    }

    @Test
    void constructor_ShouldCreateAlexWithMaleSex() {
        // Assert
        assertEquals("Самец", alex.getSex());
    }

    @Test
    void getFriends_ShouldReturnCorrectFriendsList() {
        // Act
        List<String> friends = alex.getFriends();

        // Assert
        List<String> expectedFriends = List.of("Марти", "Глория", "Мелман");
        assertEquals(expectedFriends, friends);
        assertEquals(3, friends.size());
        assertTrue(friends.contains("Марти"));
        assertTrue(friends.contains("Глория"));
        assertTrue(friends.contains("Мелман"));
    }

    @Test
    void getPlaceOfLiving_ShouldReturnNewYorkZoo() {
        // Act
        String placeOfLiving = alex.getPlaceOfLiving();

        // Assert
        assertEquals("Нью-Йоркский зоопарк", placeOfLiving);
    }

    @Test
    void getKittens_Override_ShouldReturnZero() {
        // Act
        int kittens = alex.getKittens();

        // Assert
        assertEquals(0, kittens);
    }

    @Test
    void getFamily_ShouldInheritFromLion() {
        // Arrange
        when(mockFeline.getFamily()).thenReturn("Кошачьи");

        // Act
        String family = alex.getFamily();

        // Assert
        assertEquals("Кошачьи", family);
        verify(mockFeline, times(1)).getFamily();
    }

    @Test
    void getFood_ShouldInheritFromLion() throws Exception {
        // Arrange
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(mockFeline.getFood("Хищник")).thenReturn(expectedFood);

        // Act
        List<String> actualFood = alex.getFood();

        // Assert
        assertEquals(expectedFood, actualFood);
        verify(mockFeline, times(1)).getFood("Хищник");
    }

    @Test
    void doesHaveMane_ShouldReturnTrueForAlex() {
        // Act
        boolean hasMane = alex.doesHaveMane();

        // Assert
        assertTrue(hasMane);
    }

    @Test
    void getSex_ShouldReturnMaleForAlex() {
        // Act
        String sex = alex.getSex();

        // Assert
        assertEquals("Самец", sex);
    }
}