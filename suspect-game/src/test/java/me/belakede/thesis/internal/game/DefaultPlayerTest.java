package me.belakede.thesis.internal.game;

import junitx.extensions.EqualsHashCodeTestCase;
import me.belakede.thesis.game.Player;
import me.belakede.thesis.game.equipment.Card;
import me.belakede.thesis.game.equipment.Room;
import me.belakede.thesis.game.equipment.Suspect;
import me.belakede.thesis.game.equipment.Weapon;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class DefaultPlayerTest {

    public static final class DefaultPlayerEqualsHashCodeTest extends EqualsHashCodeTestCase {

        public DefaultPlayerEqualsHashCodeTest(String name) {
            super(name);
        }

        @Override
        protected Object createInstance() throws Exception {
            Set<Card> cards = new HashSet<>();
            cards.add(Weapon.KNIFE);
            cards.add(Weapon.REVOLVER);
            cards.add(Room.BATHROOM);
            cards.add(Room.DINING_ROOM);
            cards.add(Suspect.PEACOCK);
            return new DefaultPlayer(Suspect.GREEN, cards);
        }

        @Override
        protected Object createNotEqualInstance() throws Exception {
            Set<Card> cards = new HashSet<>();
            cards.add(Weapon.LEAD_PIPE);
            cards.add(Weapon.ROPE);
            cards.add(Room.BILLIARD_ROOM);
            cards.add(Room.KITCHEN);
            cards.add(Suspect.PLUM);
            return new DefaultPlayer(Suspect.MUSTARD, cards);
        }
    }

    public static final class DefaultPlayerMiscTest {
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

    }

}