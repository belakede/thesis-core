package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.board.RoomField;
import me.belakede.thesis.game.board.SecretPassage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
}