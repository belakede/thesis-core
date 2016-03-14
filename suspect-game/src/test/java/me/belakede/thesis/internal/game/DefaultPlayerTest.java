package me.belakede.thesis.internal.game;

import me.belakede.thesis.game.Player;
import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.equipment.Card;
import me.belakede.thesis.game.equipment.Room;
import me.belakede.thesis.game.equipment.Suspect;
import me.belakede.thesis.game.equipment.Weapon;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class DefaultPlayerTest {

    private Player testSubject;

    @Before
    public void setUp() throws Exception {
        Set<Card> cards = new HashSet<>();
        cards.add(Weapon.KNIFE);
        cards.add(Weapon.REVOLVER);
        cards.add(Room.BATHROOM);
        cards.add(Room.DINING_ROOM);
        cards.add(Suspect.PEACOCK);
        testSubject = new DefaultPlayer(Suspect.GREEN, cards);
    }

    @Test
    public void testHasBeenMadeGroundlessAccusationShouldReturnFalseAfterInstantiate() throws Exception {
        assertFalse(testSubject.hasBeenMadeGroundlessAccusation());
    }

    @Test
    public void testHasBeenMadeGroundlessAccusationShouldReturnTrueAfterMakeGroundlessAccusation() throws Exception {
        testSubject.makeGroundlessAccusation();
        assertTrue(testSubject.hasBeenMadeGroundlessAccusation());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetCardsShouldThrowUnsupportedOperationExceptionWhenTryToInsertANewValue() throws Exception {
        testSubject.getCards().add(Suspect.PLUM);
    }

    @Test
    public void testHasCardShouldReturnFalseWhenArgumentIsNull() throws Exception {
        assertFalse(testSubject.hasCard(null));
    }

    @Test
    public void testHasCardShouldReturnFalseWhenCardIsNotOwnedByPlayer() throws Exception {
        assertFalse(testSubject.hasCard(Suspect.PLUM));
    }

    @Test
    public void testHasCardShouldReturnTrueWhenCardIsOwnedByPlayer() throws Exception {
        assertTrue(testSubject.hasCard(Suspect.PEACOCK));
    }

    @Test
    public void testEqualsShouldReturnTrueWhenArgumentIsTheSameObject() throws Exception {
        assertTrue(testSubject.equals(testSubject));
    }

    @Test
    public void testEqualsShouldReturnFalseWhenArgumentIsNull() throws Exception {
        assertFalse(testSubject.equals(null));
    }

    @Test
    public void testEqualsShouldReturnFalseWhenArgumentIsDifferentType() throws Exception {
        assertFalse(testSubject.equals(Mockito.mock(Field.class)));
    }

    @Test
    public void testEqualsShouldReturnFalseWhenArgumentIsDifferentPlayer() throws Exception {
        // GIVEN
        Set<Card> cards = new HashSet<>();
        cards.add(Room.HALL);
        cards.add(Room.KITCHEN);
        cards.add(Room.BEDROOM);
        cards.add(Suspect.PLUM);
        cards.add(Suspect.GREEN);
        // WHEN
        Player anotherPlayer = new DefaultPlayer(Suspect.PLUM, cards);
        // THEN
        assertFalse(testSubject.equals(anotherPlayer));
    }

    @Test
    public void testEqualsShouldReturnTrueWhenArgumentIsSimilarPlayer() throws Exception {
        // GIVEN
        Set<Card> cards = new HashSet<>();
        cards.add(Weapon.KNIFE);
        cards.add(Weapon.REVOLVER);
        cards.add(Room.BATHROOM);
        cards.add(Room.DINING_ROOM);
        cards.add(Suspect.PEACOCK);
        // WHEN
        Player anotherPlayer = new DefaultPlayer(Suspect.GREEN, cards);
        // THEN
        assertTrue(testSubject.equals(anotherPlayer));
    }

    @Test
    public void testHashCodeShouldReturnTheSameValueWhenArgumentIsSimilarRoomField() throws Exception {
        // GIVEN
        Set<Card> cards = new HashSet<>();
        cards.add(Weapon.KNIFE);
        cards.add(Weapon.REVOLVER);
        cards.add(Room.BATHROOM);
        cards.add(Room.DINING_ROOM);
        cards.add(Suspect.PEACOCK);
        // WHEN
        Player anotherPlayer = new DefaultPlayer(Suspect.GREEN, cards);
        // THEN
        assertEquals(testSubject.hashCode(), anotherPlayer.hashCode());
    }

}