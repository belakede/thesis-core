package me.belakede.thesis.internal.game.equipment;

import me.belakede.thesis.game.equipment.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultCaseTest {

    private Suspicion suspicion;
    private Case testSubject;

    @Before
    public void setUp() throws Exception {
        suspicion = new DefaultSuspicion(Suspect.GREEN, Room.BILLIARD_ROOM, Weapon.CANDLESTICK);
        testSubject = new DefaultCase(suspicion);
    }

    @Test
    public void testAccuseShouldReturnWithTheSameCaseWhenTheSuspicionWasIncorrect() throws Exception {
        // GIVEN
        Suspicion currentSuspicion = new DefaultSuspicion(Suspect.GREEN, Room.BILLIARD_ROOM, Weapon.KNIFE);
        // WHEN
        Case actual = testSubject.accuse(currentSuspicion);
        // THEN
        assertEquals(testSubject, actual);
    }

    @Test
    public void testAccuseShouldReturnWithTheANewCaseWhenTheSuspicionWasCorrect() throws Exception {
        // GIVEN
        // WHEN
        Case actual = testSubject.accuse(suspicion);
        // THEN
        assertNotEquals(testSubject, actual);
    }

    @Test
    public void testAccuseShouldReturnWithTheANewSolvedCaseWhenTheSuspicionWasCorrect() throws Exception {
        // GIVEN
        // WHEN
        Case actual = testSubject.accuse(suspicion);
        // THEN
        assertNotEquals(testSubject, actual);
        assertTrue(actual.isSolved());
    }
}