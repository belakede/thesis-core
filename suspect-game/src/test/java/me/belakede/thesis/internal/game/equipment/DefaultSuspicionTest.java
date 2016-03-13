package me.belakede.thesis.internal.game.equipment;

import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.equipment.Room;
import me.belakede.thesis.game.equipment.Suspect;
import me.belakede.thesis.game.equipment.Suspicion;
import me.belakede.thesis.game.equipment.Weapon;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class DefaultSuspicionTest {

    private Suspicion testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new DefaultSuspicion(Suspect.GREEN, Room.BILLIARD_ROOM, Weapon.CANDLESTICK);
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
        Field field = Mockito.mock(Field.class);
        assertFalse(testSubject.equals(field));
    }

    @Test
    public void testEqualsShouldReturnFalseWhenArgumentIsSuspicionButSuspectIsDifferent() throws Exception {
        Suspicion suspicion = new DefaultSuspicion(Suspect.MUSTARD, Room.BILLIARD_ROOM, Weapon.CANDLESTICK);
        assertFalse(testSubject.equals(suspicion));
    }

    @Test
    public void testEqualsShouldReturnFalseWhenArgumentIsSuspicionButRoomIsDifferent() throws Exception {
        Suspicion suspicion = new DefaultSuspicion(Suspect.GREEN, Room.DINING_ROOM, Weapon.CANDLESTICK);
        assertFalse(testSubject.equals(suspicion));
    }

    @Test
    public void testEqualsShouldReturnFalseWhenArgumentIsSuspicionButWeaponIsDifferent() throws Exception {
        Suspicion suspicion = new DefaultSuspicion(Suspect.GREEN, Room.BILLIARD_ROOM, Weapon.KNIFE);
        assertFalse(testSubject.equals(suspicion));
    }

    @Test
    public void testEqualsShouldReturnTrueWhenArgumentIsSimilarSuspicion() throws Exception {
        Suspicion suspicion = new DefaultSuspicion(Suspect.GREEN, Room.BILLIARD_ROOM, Weapon.CANDLESTICK);
        assertTrue(testSubject.equals(suspicion));
    }

    @Test
    public void testHashCodeShouldReturnTheSameValueWhenArgumentIsSimilarSuspicion() throws Exception {
        Suspicion suspicion = new DefaultSuspicion(Suspect.GREEN, Room.BILLIARD_ROOM, Weapon.CANDLESTICK);
        assertEquals(testSubject.hashCode(), suspicion.hashCode());
    }
}