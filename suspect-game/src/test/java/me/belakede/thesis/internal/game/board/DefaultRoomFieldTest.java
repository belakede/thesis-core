package me.belakede.thesis.internal.game.board;

import junitx.extensions.EqualsHashCodeTestCase;
import me.belakede.thesis.game.board.RoomField;
import me.belakede.thesis.game.equipment.Room;
import me.belakede.thesis.game.field.Field;
import me.belakede.thesis.internal.game.field.FieldFactory;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class DefaultRoomFieldTest {

    public static final class DefaultRoomFieldEqualsHashCodeTest extends EqualsHashCodeTestCase {

        public DefaultRoomFieldEqualsHashCodeTest(String name) {
            super(name);
        }

        @Override
        protected DefaultRoomField createInstance() throws Exception {
            Set<Field> fields = new HashSet<>(Arrays.asList(
                    FieldFactory.getFieldBySymbol(1, 1, 'R'), FieldFactory.getFieldBySymbol(1, 2, 'R'),
                    FieldFactory.getFieldBySymbol(2, 1, 'R'), FieldFactory.getFieldBySymbol(2, 2, 'R'),
                    FieldFactory.getFieldBySymbol(3, 1, 'R'), FieldFactory.getFieldBySymbol(3, 2, 'R')
            ));
            Set<Field> exitFields = new HashSet<>(Arrays.asList(
                    FieldFactory.getFieldBySymbol(4, 1, 'R'), FieldFactory.getFieldBySymbol(4, 2, 'R')
            ));
            return new DefaultRoomField(Room.BILLIARD_ROOM, fields, exitFields);
        }

        @Override
        protected DefaultRoomField createNotEqualInstance() throws Exception {
            Set<Field> fields = new HashSet<>(Arrays.asList(
                    FieldFactory.getFieldBySymbol(2, 2, 'R'), FieldFactory.getFieldBySymbol(2, 3, 'R'),
                    FieldFactory.getFieldBySymbol(3, 2, 'R'), FieldFactory.getFieldBySymbol(3, 3, 'R')
            ));
            Set<Field> exitFields = new HashSet<>(Arrays.asList(
                    FieldFactory.getFieldBySymbol(4, 2, 'R'), FieldFactory.getFieldBySymbol(4, 3, 'R')
            ));
            return new DefaultRoomField(Room.BILLIARD_ROOM, fields, exitFields);
        }
    }

    public static final class DefaultRoomFieldMiscTest {
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

        @Test(expected = UnsupportedOperationException.class)
        public void testGetFieldsShouldThrowUnsupportedOperationExceptionWhenTryToModify() throws Exception {
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
            Set<Field> actual = testSubject.getFields();
            actual.add(field);
        }

        @Test(expected = UnsupportedOperationException.class)
        public void testGetExitFieldsShouldThrowUnsupportedOperationExceptionWhenTryToModify() throws Exception {
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
            Set<Field> actual = testSubject.getExitFields();
            actual.add(field);
        }

        @Test
        public void testEqualsShouldReturnTrueWhenArgumentIsSame() throws Exception {
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
            // THEN
            assertTrue(testSubject.equals(testSubject));
        }

        @Test
        public void testEqualsShouldReturnFalseWhenArgumentIsNull() throws Exception {
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
            // THEN
            assertFalse(testSubject.equals(null));
        }

        @Test
        public void testEqualsShouldReturnFalseWhenArgumentIsDifferentType() throws Exception {
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
            assertFalse(testSubject.equals(field));
        }

        @Test
        public void testEqualsShouldReturnFalseWhenArgumentIsARoomFieldButRoomIsDifferent() throws Exception {
            // GIVEN
            Set<Field> fields = new HashSet<>(Arrays.asList(
                    Mockito.mock(Field.class), Mockito.mock(Field.class),
                    Mockito.mock(Field.class), Mockito.mock(Field.class)
            ));
            Set<Field> exitFields = new HashSet<>(Arrays.asList(
                    Mockito.mock(Field.class), Mockito.mock(Field.class)
            ));
            RoomField testSubject = new DefaultRoomField(Room.BILLIARD_ROOM, fields, exitFields);
            RoomField anotherRoomField = new DefaultRoomField(Room.BEDROOM, fields, exitFields);
            // WHEN
            // THEN
            assertFalse(testSubject.equals(anotherRoomField));
        }

        @Test
        public void testEqualsShouldReturnTrueWhenArgumentIsSimilarRoomField() throws Exception {
            // GIVEN
            Set<Field> fields = new HashSet<>(Arrays.asList(
                    Mockito.mock(Field.class), Mockito.mock(Field.class),
                    Mockito.mock(Field.class), Mockito.mock(Field.class)
            ));
            Set<Field> exitFields = new HashSet<>(Arrays.asList(
                    Mockito.mock(Field.class), Mockito.mock(Field.class)
            ));
            RoomField testSubject = new DefaultRoomField(Room.BILLIARD_ROOM, fields, exitFields);
            RoomField anotherRoomField = new DefaultRoomField(Room.BILLIARD_ROOM, fields, exitFields);
            // WHEN
            // THEN
            assertTrue(testSubject.equals(anotherRoomField));
        }

        @Test
        public void testHashCodeShouldReturnTheSameValueWhenArgumentIsSimilarRoomField() throws Exception {
            // GIVEN
            Set<Field> fields = new HashSet<>(Arrays.asList(
                    Mockito.mock(Field.class), Mockito.mock(Field.class),
                    Mockito.mock(Field.class), Mockito.mock(Field.class)
            ));
            Set<Field> exitFields = new HashSet<>(Arrays.asList(
                    Mockito.mock(Field.class), Mockito.mock(Field.class)
            ));
            RoomField testSubject = new DefaultRoomField(Room.BILLIARD_ROOM, fields, exitFields);
            RoomField anotherRoomField = new DefaultRoomField(Room.BILLIARD_ROOM, fields, exitFields);
            // WHEN
            // THEN
            assertEquals(testSubject.hashCode(), anotherRoomField.hashCode());
        }
    }

}