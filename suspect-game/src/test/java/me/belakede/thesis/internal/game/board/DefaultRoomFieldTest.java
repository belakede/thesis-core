package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.board.RoomField;
import me.belakede.thesis.game.equipment.Room;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class DefaultRoomFieldTest {

    @Test
    public void testGetFieldsShouldReturnWithEveryFieldsInRoomIncludeExitFields() throws Exception {
        // GIVEN
        Set<Field> fields = new HashSet<>(Arrays.asList(
                Mockito.mock(Field.class), Mockito.mock(Field.class),
                Mockito.mock(Field.class), Mockito.mock(Field.class)
        ));
        Set<Field> exitFields = new HashSet<>(Arrays.asList(
                Mockito.mock(Field.class), Mockito.mock(Field.class)
        ));
        RoomField testSubject = new DefaultRoomField(Room.BILLIARD_ROOM, fields, exitFields);
        // WHEN
        Set<Field> actual = testSubject.getFields();
        // THEN
        Set<Field> expected = new HashSet<>(fields);
        expected.addAll(exitFields);
        assertEquals(expected, actual);
    }

    @Test
    public void testIsPartOfRoomShouldReturnTrueWhenArgumentIsPartOfTheFields() throws Exception {
        // GIVEN
        Field field = Mockito.mock(Field.class);
        Set<Field> fields = new HashSet<>(Arrays.asList(
                field, Mockito.mock(Field.class),
                Mockito.mock(Field.class), Mockito.mock(Field.class)
        ));
        Set<Field> exitFields = new HashSet<>(Arrays.asList(
                Mockito.mock(Field.class), Mockito.mock(Field.class)
        ));
        RoomField testSubject = new DefaultRoomField(Room.BILLIARD_ROOM, fields, exitFields);
        // WHEN
        // THEN
        assertTrue(testSubject.isPartOfRoom(field));
    }

    @Test
    public void testIsPartOfRoomShouldReturnTrueWhenArgumentIsPartOfTheExitFields() throws Exception {
        // GIVEN
        Field field = Mockito.mock(Field.class);
        Set<Field> fields = new HashSet<>(Arrays.asList(
                Mockito.mock(Field.class), Mockito.mock(Field.class),
                Mockito.mock(Field.class), Mockito.mock(Field.class)
        ));
        Set<Field> exitFields = new HashSet<>(Arrays.asList(
                field, Mockito.mock(Field.class)
        ));
        RoomField testSubject = new DefaultRoomField(Room.BILLIARD_ROOM, fields, exitFields);
        // WHEN
        // THEN
        assertTrue(testSubject.isPartOfRoom(field));
    }

    @Test
    public void testIsPartOfRoomShouldReturnFalseWhenArgumentIsNotPartOfTheFieldsAndExitFields() throws Exception {
        // GIVEN
        Field field = Mockito.mock(Field.class);
        Set<Field> fields = new HashSet<>(Arrays.asList(
                Mockito.mock(Field.class), Mockito.mock(Field.class),
                Mockito.mock(Field.class), Mockito.mock(Field.class)
        ));
        Set<Field> exitFields = new HashSet<>(Arrays.asList(
                Mockito.mock(Field.class), Mockito.mock(Field.class)
        ));
        RoomField testSubject = new DefaultRoomField(Room.BILLIARD_ROOM, fields, exitFields);
        // WHEN
        // THEN
        assertFalse(testSubject.isPartOfRoom(field));
    }

}