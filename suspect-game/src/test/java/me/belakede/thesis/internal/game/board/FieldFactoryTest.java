package me.belakede.thesis.internal.game.board;

import com.google.code.tempusfugit.concurrency.IntermittentTestRunner;
import com.google.code.tempusfugit.concurrency.annotations.Intermittent;
import me.belakede.thesis.game.board.Field;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(IntermittentTestRunner.class)
public class FieldFactoryTest {

    @Test
    @Intermittent(repetition = 10)
    public void testGetNullFieldShouldReturnAlwaysWithTheSameNullField() throws Exception {
        Field expected = FieldFactory.getNullField();
        Field actual = FieldFactory.getNullField();

        assertEquals(expected, actual);
    }

}