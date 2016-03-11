package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.equipment.Case;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


public class CasesTest {

    @Test
    public void testGenerateShouldReturnValidCase() throws Exception {
        // GIVEN
        Case testSubject = Cases.generate();
        // WHEN
        // THEN
        assertNotNull(testSubject);
        assertNotNull(testSubject.getRoom());
        assertNotNull(testSubject.getSuspect());
        assertNotNull(testSubject.getWeapon());
        assertFalse(testSubject.isSolved());
    }
}