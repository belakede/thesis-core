package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.equipment.Suspect;
import me.belakede.thesis.game.equipment.Weapon;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FigurinesTest {

    @Test
    public void testGetNumberOfFigurinesShouldReturnTheNumberOfSuspectsPlusNumberOfWeapons() throws Exception {
        // GIVEN
        int numberOfSuspects = Suspect.values().length;
        int numberOfWeapons = Weapon.values().length;
        // WHEN
        // THEN
        int expected = numberOfSuspects + numberOfWeapons;
        int actual = Figurines.getNumberOfFigurines();
        assertEquals(expected, actual);
    }

    @Test
    public void testValuesShouldReturnAnTwelveElementSet() throws Exception {
        // GIVEN
        int numberOfSuspects = Suspect.values().length;
        int numberOfWeapons = Weapon.values().length;
        // WHEN
        // THEN
        int expected = numberOfSuspects + numberOfWeapons;
        int actual = Figurines.values().size();
        assertEquals(expected, actual);
    }
}