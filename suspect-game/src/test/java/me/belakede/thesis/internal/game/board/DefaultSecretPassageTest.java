package me.belakede.thesis.internal.game.board;

import junitx.extensions.EqualsHashCodeTestCase;
import me.belakede.thesis.game.board.RoomField;
import me.belakede.thesis.game.board.SecretPassage;
import me.belakede.thesis.game.equipment.Room;
import me.belakede.thesis.game.equipment.Suspicion;
import me.belakede.thesis.game.field.Field;
import me.belakede.thesis.internal.game.field.FieldFactory;
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
                    FieldFactory.getFieldBySymbol(1, 1, 'R'), FieldFactory.getFieldBySymbol(1, 2, 'R')
            ));
            Set<Field> fromExitFields = new HashSet<>(Arrays.asList(
                    FieldFactory.getFieldBySymbol(2, 1, 'R'), FieldFactory.getFieldBySymbol(2, 2, 'R')
            ));
            DefaultRoomField from = new DefaultRoomField(Room.BILLIARD_ROOM, fromFields, fromExitFields);
            Set<Field> toFields = new HashSet<>(Arrays.asList(
                    FieldFactory.getFieldBySymbol(10, 9, 'R'), FieldFactory.getFieldBySymbol(10, 10, 'R')
            ));
            Set<Field> toExitFields = new HashSet<>(Arrays.asList(
                    FieldFactory.getFieldBySymbol(9, 9, 'R'), FieldFactory.getFieldBySymbol(9, 10, 'R')
            ));
            DefaultRoomField to = new DefaultRoomField(Room.BALLROOM, toFields, toExitFields);
            return new DefaultSecretPassage(from, to);
        }

        @Override
        protected DefaultSecretPassage createNotEqualInstance() throws Exception {
            Set<Field> fromFields = new HashSet<>(Arrays.asList(
                    FieldFactory.getFieldBySymbol(5, 5, 'R'), FieldFactory.getFieldBySymbol(5, 6, 'R')
            ));
            Set<Field> fromExitFields = new HashSet<>(Arrays.asList(
                    FieldFactory.getFieldBySymbol(6, 5, 'R'), FieldFactory.getFieldBySymbol(6, 6, 'R')
            ));
            DefaultRoomField from = new DefaultRoomField(Room.KITCHEN, fromFields, fromExitFields);
            Set<Field> toFields = new HashSet<>(Arrays.asList(
                    FieldFactory.getFieldBySymbol(10, 1, 'R'), FieldFactory.getFieldBySymbol(10, 2, 'R')
            ));
            Set<Field> toExitFields = new HashSet<>(Arrays.asList(
                    FieldFactory.getFieldBySymbol(9, 1, 'R'), FieldFactory.getFieldBySymbol(9, 2, 'R')
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