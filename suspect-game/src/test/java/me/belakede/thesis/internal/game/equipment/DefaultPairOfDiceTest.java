package me.belakede.thesis.internal.game.equipment;

import com.google.code.tempusfugit.concurrency.IntermittentTestRunner;
import com.google.code.tempusfugit.concurrency.annotations.Intermittent;
import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.equipment.PairOfDice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.*;

@RunWith(IntermittentTestRunner.class)
public class DefaultPairOfDiceTest {

    private PairOfDice testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = DefaultPairOfDice.create();
    }

    @Test
    @Intermittent(repetition = 25)
    public void testRollAlwaysShouldReturnANewPairOfDiceWhereResultAlwaysLowerThanThirteen() throws Exception {
        PairOfDice testSubject = DefaultPairOfDice.create();
        PairOfDice roll = testSubject.roll();
        assertTrue(roll.getResult() < 13);
    }

    @Test
    public void testEqualsShouldReturnTrueWhenArgumentIsSame() throws Exception {
        assertTrue(testSubject.equals(testSubject));
    }

    @Test
    public void testEqualsShouldReturnFalseWhenArgumentIsNull() throws Exception {
        assertFalse(testSubject.equals(null));
    }

    @Test
    public void testEqualsShouldReturnFalseWhenArgumentIsDifferentType() throws Exception {
        assertFalse(testSubject.equals(Mockito.mock(Field.class)));
    }

    @Test
    public void testEqualsShouldReturnFalseWhenArgumentIsAPairOfDiceButValueIsDifferent() throws Exception {
        PairOfDice dice = DefaultPairOfDice.create();
        while (testSubject.getFirst() == dice.getFirst()) {
            dice = dice.roll();
        }
        assertFalse(testSubject.equals(dice));
    }

    @Test
    public void testEqualsShouldReturnTrueWhenArgumentIsSimilarRoomField() throws Exception {
        PairOfDice dice = DefaultPairOfDice.create();
        while (testSubject.getFirst() != dice.getFirst() || testSubject.getSecond() != dice.getSecond()) {
            dice = dice.roll();
        }
        assertTrue(testSubject.equals(dice));
    }

    @Test
    public void testHashCodeShouldReturnTheSameValueWhenArgumentIsSimilarRoomField() throws Exception {
        PairOfDice dice = DefaultPairOfDice.create();
        while (testSubject.getFirst() != dice.getFirst() || testSubject.getSecond() != dice.getSecond()) {
            dice = dice.roll();
        }
        assertEquals(testSubject.hashCode(), dice.hashCode());
    }

}