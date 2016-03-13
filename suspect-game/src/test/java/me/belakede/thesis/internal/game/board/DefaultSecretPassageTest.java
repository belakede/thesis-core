package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.board.RoomField;
import me.belakede.thesis.game.board.SecretPassage;
import me.belakede.thesis.game.equipment.Suspicion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class DefaultSecretPassageTest {

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