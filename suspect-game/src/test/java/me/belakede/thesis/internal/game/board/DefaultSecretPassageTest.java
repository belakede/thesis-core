package me.belakede.thesis.internal.game.board;

import junitx.extensions.EqualsHashCodeTestCase;
import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.board.FieldType;
import me.belakede.thesis.game.board.RoomField;
import me.belakede.thesis.game.board.SecretPassage;
import me.belakede.thesis.game.equipment.Room;
import me.belakede.thesis.game.equipment.Suspicion;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class DefaultSecretPassageTest {

    public static final class DefaultSecretPassageEqualsHashCodeTest extends EqualsHashCodeTestCase {

        public DefaultSecretPassageEqualsHashCodeTest(String name) {
            super(name);
        }

        @Override
        protected DefaultSecretPassage createInstance() throws Exception {
            Set<Field> fromFields = new HashSet<>(Arrays.asList(
                    new DefaultField(FieldType.ROOM, 1, 1), new DefaultField(FieldType.ROOM, 1, 2)
            ));
            Set<Field> fromExitFields = new HashSet<>(Arrays.asList(
                    new DefaultField(FieldType.ROOM, 2, 1), new DefaultField(FieldType.ROOM, 2, 2)
            ));
            DefaultRoomField from = new DefaultRoomField(Room.BILLIARD_ROOM, fromFields, fromExitFields);
            Set<Field> toFields = new HashSet<>(Arrays.asList(
                    new DefaultField(FieldType.ROOM, 10, 9), new DefaultField(FieldType.ROOM, 10, 10)
            ));
            Set<Field> toExitFields = new HashSet<>(Arrays.asList(
                    new DefaultField(FieldType.ROOM, 9, 9), new DefaultField(FieldType.ROOM, 9, 10)
            ));
            DefaultRoomField to = new DefaultRoomField(Room.BALLROOM, toFields, toExitFields);
            return new DefaultSecretPassage(from, to);
        }

        @Override
        protected DefaultSecretPassage createNotEqualInstance() throws Exception {
            Set<Field> fromFields = new HashSet<>(Arrays.asList(
                    new DefaultField(FieldType.ROOM, 5, 5), new DefaultField(FieldType.ROOM, 5, 6)
            ));
            Set<Field> fromExitFields = new HashSet<>(Arrays.asList(
                    new DefaultField(FieldType.ROOM, 6, 5), new DefaultField(FieldType.ROOM, 6, 6)
            ));
            DefaultRoomField from = new DefaultRoomField(Room.KITCHEN, fromFields, fromExitFields);
            Set<Field> toFields = new HashSet<>(Arrays.asList(
                    new DefaultField(FieldType.ROOM, 10, 1), new DefaultField(FieldType.ROOM, 10, 2)
            ));
            Set<Field> toExitFields = new HashSet<>(Arrays.asList(
                    new DefaultField(FieldType.ROOM, 9, 1), new DefaultField(FieldType.ROOM, 9, 2)
            ));
            DefaultRoomField to = new DefaultRoomField(Room.BATHROOM, toFields, toExitFields);
            return new DefaultSecretPassage(from, to);
        }
    }

    public static final class DefaultSecretPassageMiscTest {
        @Mock
        private RoomField from;
        @Mock
        private RoomField to;
        private SecretPassage testSubject;

        @Before
        public void setUp() throws Exception {
            MockitoAnnotations.initMocks(this);
            testSubject = new DefaultSecretPassage(from, to);
        }

        @Test
        public void testIsPartOfSecretPassageShouldReturnTrueWhenArgumentIsFromField() throws Exception {
            assertTrue(testSubject.isPartOfSecretPassage(from));
        }

        @Test
        public void testIsPartOfSecretPassageShouldReturnTrueWhenArgumentIsToField() throws Exception {
            assertTrue(testSubject.isPartOfSecretPassage(to));
        }

        @Test
        public void testIsPartOfSecretPassageShouldReturnFalseWhenArgumentIsNull() throws Exception {
            assertFalse(testSubject.isPartOfSecretPassage(null));
        }

        @Test
        public void testIsPartOfSecretPassageShouldReturnFalseWhenArgumentIsAnotherRoomField() throws Exception {
            // GIVEN
            RoomField field = Mockito.mock(RoomField.class);
            // THEN
            assertFalse(testSubject.isPartOfSecretPassage(field));
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
            Suspicion suspicion = Mockito.mock(Suspicion.class);
            assertFalse(testSubject.equals(suspicion));
        }

        @Test
        public void testEqualsShouldReturnFalseWhenArgumentIsASecretPassageButFromIsDifferent() throws Exception {
            RoomField from = Mockito.mock(RoomField.class);
            SecretPassage secretPassage = new DefaultSecretPassage(from, to);
            assertFalse(testSubject.equals(secretPassage));
        }

        @Test
        public void testEqualsShouldReturnFalseWhenArgumentIsASecretPassageButToIsDifferent() throws Exception {
            RoomField to = Mockito.mock(RoomField.class);
            SecretPassage secretPassage = new DefaultSecretPassage(from, to);
            assertFalse(testSubject.equals(secretPassage));
        }

        @Test
        public void testEqualsShouldReturnFalseWhenArgumentIsASecretPassageButRoomFieldsAreDifferent() throws Exception {
            RoomField from = Mockito.mock(RoomField.class);
            RoomField to = Mockito.mock(RoomField.class);
            SecretPassage secretPassage = new DefaultSecretPassage(from, to);
            assertFalse(testSubject.equals(secretPassage));
        }

        @Test
        public void testEqualsShouldReturnTrueWhenArgumentIsASimilarSecretPassage() throws Exception {
            SecretPassage secretPassage = new DefaultSecretPassage(from, to);
            assertTrue(testSubject.equals(secretPassage));
        }

        @Test
        public void testHashCodeShouldReturnTheSameValueWhenArgumentIsASimilarSecretPassage() throws Exception {
            SecretPassage secretPassage = new DefaultSecretPassage(from, to);
            assertEquals(testSubject.hashCode(), secretPassage.hashCode());
        }
    }

}