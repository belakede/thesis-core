package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.board.FieldType;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class DefaultFieldTest {

    @Test
    public void testCanMakeAnAccusationShouldReturnTrueWhenFieldIsAnEndField() throws Exception {
        Field testSubject = new DefaultField(FieldType.END, 1, 1);
        assertTrue(testSubject.canMakeAnAccusation());
    }

    @Test
    public void testCanMakeAnAccusationShouldReturnFalseWhenFieldIsAStartField() throws Exception {
        Field testSubject = new DefaultField(FieldType.START, 1, 1);
        assertFalse(testSubject.canMakeAnAccusation());
    }

    @Test
    public void testCanMakeAnAccusationShouldReturnFalseWhenFieldIsASimpleField() throws Exception {
        Field testSubject = new DefaultField(FieldType.SIMPLE, 1, 1);
        assertFalse(testSubject.canMakeAnAccusation());
    }

    @Test
    public void testCanMakeAnAccusationShouldReturnFalseWhenFieldIsAWallField() throws Exception {
        Field testSubject = new DefaultField(FieldType.WALL, 1, 1);
        assertFalse(testSubject.canMakeAnAccusation());
    }

    @Test
    public void testCanMakeAnAccusationShouldReturnFalseWhenFieldIsARoomField() throws Exception {
        Field testSubject = new DefaultField(FieldType.ROOM, 1, 1);
        assertFalse(testSubject.canMakeAnAccusation());
    }

    @Test
    public void testCanMakeAnAccusationShouldReturnFalseWhenFieldIsANullField() throws Exception {
        Field testSubject = new DefaultField(FieldType.ROOM, 1, 1);
        assertFalse(testSubject.canMakeAnAccusation());
    }

}