package me.belakede.thesis.internal.game;

import me.belakede.thesis.game.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;


public class DefaultPlayerCycleTest {

    @Mock
    private Player first;
    private DefaultPlayerCycle testSubject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        testSubject = new DefaultPlayerCycle(first);
    }

    @Test
    public void testSizeShouldReturnWithOneAfterInstantiate() throws Exception {
        // GIVEN
        // WHEN
        // THEN
        int expected = 1;
        int actual = testSubject.size();
        assertEquals(expected, actual);
    }

    @Test
    public void testSizeShouldReturnWithOneAfterAppendFirstPlayerAgain() throws Exception {
        // GIVEN
        // WHEN
        testSubject.append(first);
        // THEN
        int expected = 1;
        int actual = testSubject.size();
        assertEquals(expected, actual);
    }

    @Test
    public void testSizeShouldReturnWithThreeAfterAppendTwoAnotherPlayers() throws Exception {
        // GIVEN
        Player second = Mockito.mock(Player.class);
        Player third = Mockito.mock(Player.class);
        // WHEN
        testSubject.append(second);
        testSubject.append(third);
        // THEN
        int expected = 3;
        int actual = testSubject.size();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetCurrentShouldReturnWithFirstPlayerAfterInstantiate() throws Exception {
        // GIVEN
        // WHEN
        // THEN
        Player expected = first;
        Player actual = testSubject.getCurrent();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetCurrentShouldReturnWithFirstPlayerAfterAppendTwoAnotherPlayers() throws Exception {
        // GIVEN
        Player second = Mockito.mock(Player.class);
        Player third = Mockito.mock(Player.class);
        // WHEN
        testSubject.append(second);
        testSubject.append(third);
        // THEN
        Player expected = first;
        Player actual = testSubject.getCurrent();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNextShouldReturnWithSecondPlayerAfterAppendTwoAnotherPlayers() throws Exception {
        // GIVEN
        Player second = Mockito.mock(Player.class);
        // WHEN
        testSubject.append(second);
        // THEN
        Player expected = second;
        Player actual = testSubject.getNext();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetCurrentShouldBeReturnWithSecondPlayerAfterInvokeNext() throws Exception {
        // GIVEN
        Player second = Mockito.mock(Player.class);
        // WHEN
        testSubject.append(second);
        testSubject.next();
        // THEN
        Player expected = second;
        Player actual = testSubject.getCurrent();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNextShouldReturnWithFirstPlayerAfterInvokeNext() throws Exception {
        // GIVEN
        Player second = Mockito.mock(Player.class);
        // WHEN
        testSubject.append(second);
        testSubject.next();
        // THEN
        Player expected = first;
        Player actual = testSubject.getNext();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetCurrentShouldReturnWithFirstPlayerAfterAppendTwoAnotherPlayerAndInvokeNextThrice() throws Exception {
        // GIVEN
        Player second = Mockito.mock(Player.class);
        Player third = Mockito.mock(Player.class);
        // WHEN
        testSubject.append(second);
        testSubject.append(third);
        testSubject.next();
        testSubject.next();
        testSubject.next();
        // THEN
        Player expected = first;
        Player actual = testSubject.getCurrent();
        assertEquals(expected, actual);
    }

    @Test
    public void testFirstShouldSetTheCurrentPlayerToTheeFirstPlayer() throws Exception {
        // GIVEN
        Player second = Mockito.mock(Player.class);
        Player third = Mockito.mock(Player.class);
        // WHEN
        testSubject.append(second);
        testSubject.append(third);
        testSubject.next();
        testSubject.first();
        // THEN
        Player expected = first;
        Player actual = testSubject.getCurrent();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNumberOfPlayersShouldReturnOneWhenSizeIsTwoButOnePlayerMadeGroundlessAccusation() throws Exception {
        // GIVEN
        Player second = Mockito.mock(Player.class);
        // WHEN
        testSubject.append(second);
        Mockito.when(first.hasBeenMadeGroundlessAccusation()).thenReturn(false);
        Mockito.when(second.hasBeenMadeGroundlessAccusation()).thenReturn(true);
        // THEN
        int expected = 1;
        int actual = testSubject.getNumberOfPlayers();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNumberOfPlayersShouldReturnTwoWhenSizeIsFourButTwoPlayersMadeGroundlessAccusation() throws Exception {
        // GIVEN
        Player second = Mockito.mock(Player.class);
        Player third = Mockito.mock(Player.class);
        Player fourth = Mockito.mock(Player.class);
        // WHEN
        testSubject.append(second);
        testSubject.append(third);
        testSubject.append(fourth);
        Mockito.when(first.hasBeenMadeGroundlessAccusation()).thenReturn(false);
        Mockito.when(second.hasBeenMadeGroundlessAccusation()).thenReturn(true);
        Mockito.when(third.hasBeenMadeGroundlessAccusation()).thenReturn(true);
        Mockito.when(fourth.hasBeenMadeGroundlessAccusation()).thenReturn(false);
        // THEN
        int expected = 2;
        int actual = testSubject.getNumberOfPlayers();
        assertEquals(expected, actual);
    }

    @Test
    public void testHasNextShouldReturnFalseWhenSizeIsTwoButOnePlayerMadeGroundlessAccusation() throws Exception {
        // GIVEN
        Player second = Mockito.mock(Player.class);
        // WHEN
        testSubject.append(second);
        Mockito.when(first.hasBeenMadeGroundlessAccusation()).thenReturn(false);
        Mockito.when(second.hasBeenMadeGroundlessAccusation()).thenReturn(true);
        // THEN
        boolean actual = testSubject.hasNext();
        assertFalse(actual);
    }

    @Test
    public void testHasNextShouldReturnTrueWhenSizeIsFourButTwoPlayersMadeGroundlessAccusation() throws Exception {
        // GIVEN
        Player second = Mockito.mock(Player.class);
        Player third = Mockito.mock(Player.class);
        Player fourth = Mockito.mock(Player.class);
        // WHEN
        testSubject.append(second);
        testSubject.append(third);
        testSubject.append(fourth);
        Mockito.when(first.hasBeenMadeGroundlessAccusation()).thenReturn(false);
        Mockito.when(second.hasBeenMadeGroundlessAccusation()).thenReturn(true);
        Mockito.when(third.hasBeenMadeGroundlessAccusation()).thenReturn(true);
        Mockito.when(fourth.hasBeenMadeGroundlessAccusation()).thenReturn(false);
        // THEN
        boolean actual = testSubject.hasNext();
        assertTrue(actual);
    }
}