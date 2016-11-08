package me.belakede.thesis.internal.game.field;

import com.google.code.tempusfugit.concurrency.IntermittentTestRunner;
import com.google.code.tempusfugit.concurrency.annotations.Intermittent;
import me.belakede.thesis.game.field.Field;
import me.belakede.thesis.game.field.FieldType;
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

    @Test
    public void testGetFieldByCharacterShouldReturnWithRoomFieldWhenArgumentIsAnR() throws Exception {
        // WHEN
        Field actual = FieldFactory.getFieldBySymbol(0, 0, 'R');
        assertEquals(FieldType.ROOM, actual.getFieldType());
    }

    @Test
    public void testGetFieldByCharacterShouldReturnWithWallFieldWhenArgumentIsAW() throws Exception {
        // WHEN
        Field actual = FieldFactory.getFieldBySymbol(0, 0, 'W');
        assertEquals(FieldType.WALL, actual.getFieldType());
    }

    @Test
    public void testGetFieldByCharacterShouldReturnWithSimpleFieldWhenArgumentIsAnF() throws Exception {
        // WHEN
        Field actual = FieldFactory.getFieldBySymbol(0, 0, 'F');
        assertEquals(FieldType.SIMPLE, actual.getFieldType());
    }

    @Test
    public void testGetFieldByCharacterShouldReturnWithStartFieldWhenArgumentIsAnS() throws Exception {
        // WHEN
        Field actual = FieldFactory.getFieldBySymbol(0, 0, 'S');
        assertEquals(FieldType.START, actual.getFieldType());
    }

    @Test
    public void testGetFieldByCharacterShouldReturnWithEndFieldWhenArgumentIsAnE() throws Exception {
        // WHEN
        Field actual = FieldFactory.getFieldBySymbol(0, 0, 'E');
        assertEquals(FieldType.END, actual.getFieldType());
    }

    @Test
    public void testGetFieldByCharacterShouldReturnWithNullFieldWhenArgumentIsSomethingElse() throws Exception {
        // WHEN
        Field actual = FieldFactory.getFieldBySymbol(0, 0, 'K');
        assertEquals(FieldType.NULL, actual.getFieldType());
    }

}