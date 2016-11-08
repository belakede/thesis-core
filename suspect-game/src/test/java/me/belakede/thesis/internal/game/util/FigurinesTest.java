package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.board.Board;
import me.belakede.thesis.game.equipment.*;
import me.belakede.thesis.game.field.Field;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class FigurinesTest {

    @Test
    public void testValueOfShouldReturnEmptyOptionalWhenNameIsNull() {
        // GIVEN
        String name = null;
        // WHEN
        Optional<Figurine> actual = Figurines.valueOf(name);
        // THEN
        assertFalse(actual.isPresent());
    }

    @Test
    public void testValueOfShouldReturnEmptyOptionalWhenNameIsInvalid() {
        // GIVEN
        String name = "";
        // WHEN
        Optional<Figurine> actual = Figurines.valueOf(name);
        // THEN
        assertFalse(actual.isPresent());
    }

    @Test
    public void testValueOfShouldReturnOptionalDescribingEnumConstantWhenNameIsValid() {
        // GIVEN
        String name = Weapon.KNIFE.name();
        // WHEN
        Optional<Figurine> actual = Figurines.valueOf(name);
        // THEN
        Figurine expected = Weapon.KNIFE;

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    public void testGetNumberOfFigurinesShouldReturnTheNumberOfSuspectsPlusNumberOfWeapons() throws Exception {
        // GIVEN
        int numberOfSuspects = Suspect.values().length;
        int numberOfWeapons = Weapon.values().length;
        // WHEN
        // THEN
        int expected = numberOfSuspects + numberOfWeapons;
        int actual = Figurines.getNumberOfFigurines();
        assertEquals(expected, actual);
    }

    @Test
    public void testValuesShouldReturnAnTwelveElementSet() throws Exception {
        // GIVEN
        int numberOfSuspects = Suspect.values().length;
        int numberOfWeapons = Weapon.values().length;
        // WHEN
        // THEN
        int expected = numberOfSuspects + numberOfWeapons;
        int actual = Figurines.values().size();
        assertEquals(expected, actual);
    }

    @Test
    public void testFigurinesCardsShouldReturnAMapContainsAllCardsExceptTheSpecifiedMysterySeparatedByFigurines() {
        // GIVEN
        Case mystery = Cases.generate();
        int numberOfPlayers = 6;
        // WHEN
        Set<Card> actual = new HashSet<>();
        Figurines.cards(numberOfPlayers, mystery).values().forEach(actual::addAll);
        // THEN
        Set<Card> expected = new HashSet<>(Cards.values());
        expected.remove(mystery.getRoom());
        expected.remove(mystery.getSuspect());
        expected.remove(mystery.getWeapon());
        assertEquals(expected, actual);
        assertEquals(21 - 3, expected.size());
    }

    @Test
    public void testFigurinesCardsShouldReturnAMapContainsAllCardsExceptTheSpecifiedMysterySeparatedByFigurinesWhenNumberOfPlayersIsFive() {
        // GIVEN
        Case mystery = Cases.generate();
        int numberOfPlayers = 5;
        // WHEN
        Set<Card> actual = new HashSet<>();
        Figurines.cards(numberOfPlayers, mystery).values().forEach(actual::addAll);
        // THEN
        Set<Card> expected = new HashSet<>(Cards.values());
        expected.remove(mystery.getRoom());
        expected.remove(mystery.getSuspect());
        expected.remove(mystery.getWeapon());
        assertEquals(expected, actual);
        assertEquals(21 - 3, expected.size());
    }

    @Test
    public void testStartingPositionsShouldReturnAMapWhichContainsAllFigurines() throws Exception {
        // GIVEN
        Board board = Boards.getDefaultBoard();
        // WHEN
        Set<Figurine> actual = Figurines.startingPositions(board).keySet();
        // THEN
        Set<Figurine> expected = Figurines.values();
        assertEquals(expected, actual);
    }

    @Test
    public void testStartingPositionsShouldReturnAMapWhereThereIsNoNullValue() throws Exception {
        // GIVEN
        Board board = Boards.getDefaultBoard();
        // WHEN
        Collection<Field> actual = Figurines.startingPositions(board).values();
        // THEN
        actual.forEach(Assert::assertNotNull);
    }

    @Test
    public void testStartingPositionsShouldReturnADifferentMapAfterEveryCall() throws Exception {
        // GIVEN
        Board board = Boards.getDefaultBoard();
        // WHEN
        Map<Figurine, Field> first = Figurines.startingPositions(board);
        Map<Figurine, Field> second = Figurines.startingPositions(board);
        // THEN
        assertNotEquals(first, second);
    }

}