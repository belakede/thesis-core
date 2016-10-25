package me.belakede.thesis.internal.game.board;

import junitx.extensions.EqualsHashCodeTestCase;
import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.board.FieldType;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class DefaultFieldTest {


    public static final class DefaultFieldEqualsHashCodeTest extends EqualsHashCodeTestCase {

        public DefaultFieldEqualsHashCodeTest(String name) {
            super(name);
        }

        @Override
        protected DefaultField createInstance() throws Exception {
            return new DefaultField(FieldType.SIMPLE, 1, 1);
        }

        @Override
        protected DefaultField createNotEqualInstance() throws Exception {
            return new DefaultField(FieldType.SIMPLE, 1, 2);
        }
    }

    public static final class DefaultFieldMiscTest {
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

        @Test
        public void testEqualsShouldReturnTrueWhenArgumentIsSimilar() throws Exception {
            Field testSubject = new DefaultField(FieldType.ROOM, 1, 1);
            Field copyOfTestSubject = new DefaultField(FieldType.ROOM, 1, 1);
            assertTrue(testSubject.equals(copyOfTestSubject));
            assertTrue(copyOfTestSubject.equals(testSubject));
        }

        @Test
        public void testEqualsShouldReturnTrueWhenArgumentIsSame() throws Exception {
            Field testSubject = new DefaultField(FieldType.ROOM, 1, 1);
            assertTrue(testSubject.equals(testSubject));
        }

        @Test
        public void testEqualsShouldReturnFalseWhenArgumentIsDifferent() throws Exception {
            Field testSubject = new DefaultField(FieldType.ROOM, 1, 1);
            Field anotherField = new DefaultField(FieldType.SIMPLE, 4, 2);
            assertFalse(testSubject.equals(anotherField));
        }

        @Test
        public void testEqualsShouldReturnFalseWhenArgumentIsNull() throws Exception {
            Field testSubject = new DefaultField(FieldType.ROOM, 1, 1);
            assertFalse(testSubject.equals(null));
        }

        @Test
        public void testHashCodeShouldReturnTheSameHashCodeWhenArgumentIsSimilar() throws Exception {
            Field testSubject = new DefaultField(FieldType.ROOM, 1, 1);
            Field copyOfTestSubject = new DefaultField(FieldType.ROOM, 1, 1);
            assertEquals(testSubject.hashCode(), copyOfTestSubject.hashCode());
        }

        @Test
        public void testHashCodeShouldReturnTheSameHashCodeWhenArgumentIsSame() throws Exception {
            Field testSubject = new DefaultField(FieldType.ROOM, 1, 1);
            assertEquals(testSubject.hashCode(), testSubject.hashCode());
        }

        @Test
        public void testHashCodeShouldReturnDifferentValuesWhenArgumentIsDifferent() throws Exception {
            Field testSubject = new DefaultField(FieldType.ROOM, 1, 1);
            Field anotherField = new DefaultField(FieldType.SIMPLE, 4, 2);
            assertNotEquals(testSubject.hashCode(), anotherField.hashCode());
        }
    }
}