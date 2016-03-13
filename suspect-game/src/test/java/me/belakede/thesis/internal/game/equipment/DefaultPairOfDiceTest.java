package me.belakede.thesis.internal.game.equipment;

import com.google.code.tempusfugit.concurrency.IntermittentTestRunner;
import com.google.code.tempusfugit.concurrency.annotations.Intermittent;
import me.belakede.thesis.game.equipment.PairOfDice;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(IntermittentTestRunner.class)
public class DefaultPairOfDiceTest {

    @Test
    @Intermittent(repetition = 25)
    public void testRollAlwaysShouldReturnANewPairOfDiceWhereResultAlwaysLowerThanThirteen() throws Exception {
        PairOfDice testSubject = DefaultPairOfDice.create();
        PairOfDice roll = testSubject.roll();
        assertTrue(roll.getResult() < 13);
    }
}