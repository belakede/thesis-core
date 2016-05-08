package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.board.Board;
import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.equipment.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FigurinesTest {

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