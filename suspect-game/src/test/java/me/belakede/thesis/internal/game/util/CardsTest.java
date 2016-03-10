package me.belakede.thesis.internal.game.util;

import com.google.code.tempusfugit.concurrency.IntermittentTestRunner;
import com.google.code.tempusfugit.concurrency.annotations.Intermittent;
import me.belakede.thesis.game.equipment.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.Assert.*;


@RunWith(IntermittentTestRunner.class)
public class CardsTest {

    @Test
    public void testValueOfShouldReturnEmptyOptionalWhenNameIsNull() {
        // GIVEN
        String name = null;
        // WHEN
        Optional<Card> actual = Cards.valueOf(name);
        // THEN
        assertFalse(actual.isPresent());
    }

    @Test
    public void testValueOfShouldReturnEmptyOptionalWhenNameIsInvalid() {
        // GIVEN
        String name = "";
        // WHEN
        Optional<Card> actual = Cards.valueOf(name);
        // THEN
        assertFalse(actual.isPresent());
    }

    @Test
    public void testValueOfShouldReturnOptionalDescribingEnumConstantWhenNameIsValid() {
        // GIVEN
        String name = Weapon.KNIFE.name();
        // WHEN
        Optional<Card> actual = Cards.valueOf(name);
        // THEN
        Card expected = Weapon.KNIFE;

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test(expected = NullPointerException.class)
    public void testShuffledValuesExceptShouldThrowNullPointerExceptionWhenCaseIsNull() {
        // GIVEN
        Case mystery = null;
        // WHEN
        Cards.shuffledValuesExcept(mystery);
        // THEN
    }

    @Test
    public void testShuffledValuesExceptShouldReturnTheFilteredListWhenCaseIsValid() {
        // GIVEN
        Case mystery = Mockito.mock(Case.class);
        Mockito.when(mystery.getRoom()).thenReturn(Room.BALLROOM);
        Mockito.when(mystery.getSuspect()).thenReturn(Suspect.GREEN);
        Mockito.when(mystery.getWeapon()).thenReturn(Weapon.CANDLESTICK);
        // WHEN
        List<Card> actual = Cards.shuffledValuesExcept(mystery);
        // THEN
        assertFalse(actual.contains(Room.BALLROOM));
        assertFalse(actual.contains(Suspect.GREEN));
        assertFalse(actual.contains(Weapon.CANDLESTICK));
    }

    @Test
    public void testValuesShouldReturnWithAllOfTheCards() {
        // GIVEN
        // WHEN
        Set<Card> actual = Cards.values();
        // THEN
        Set<Card> expected = new HashSet<>();
        expected.addAll(Arrays.asList(Room.values()));
        expected.addAll(Arrays.asList(Suspect.values()));
        expected.addAll(Arrays.asList(Weapon.values()));

        assertEquals(expected, actual);
    }

    @Test
    @Intermittent(repetition = 25)
    public void testGetRandomRoomShouldAlwaysReturnAnOptionalDescribingARoom() {
        // GIVEN
        Set<Room> rooms = new HashSet<>(Arrays.asList(Room.values()));
        // WHEN
        Optional<Room> actual = Cards.getRandomRoom();
        // THEN
        assertTrue(actual.isPresent());
        assertTrue(rooms.contains(actual.get()));
    }

    @Test
    @Intermittent(repetition = 25)
    public void testGetRandomSuspectShouldAlwaysReturnAnOptionalDescribingASuspect() {
        // GIVEN
        Set<Suspect> suspects = new HashSet<>(Arrays.asList(Suspect.values()));
        // WHEN
        Optional<Suspect> actual = Cards.getRandomSuspect();
        // THEN
        assertTrue(actual.isPresent());
        assertTrue(suspects.contains(actual.get()));
    }

    @Test
    @Intermittent(repetition = 25)
    public void testGetRandomWeaponShouldAlwaysReturnAnOptionalDescribingAWeapon() {
        // GIVEN
        Set<Weapon> weapons = new HashSet<>(Arrays.asList(Weapon.values()));
        // WHEN
        Optional<Weapon> actual = Cards.getRandomWeapon();
        // THEN
        assertTrue(actual.isPresent());
        assertTrue(weapons.contains(actual.get()));
    }

    @Test
    public void testGetNumberOfCardsShouldReturnTheNumberOfRoomsPlusNumberOfWeaponsPlusNumberOfSuspects() throws Exception {
        // GIVEN
        int numberOfRooms = Room.values().length;
        int numberOfSuspects = Suspect.values().length;
        int numberOfWeapons = Weapon.values().length;
        // WHEN
        int actual = Cards.getNumberOfCard();
        // THEN
        int expected = numberOfRooms + numberOfWeapons + numberOfSuspects;
        assertEquals(expected, actual);
    }
}
