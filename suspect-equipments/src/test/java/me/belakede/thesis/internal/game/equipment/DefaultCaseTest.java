package me.belakede.thesis.internal.game.equipment;

import junitx.extensions.EqualsHashCodeTestCase;
import me.belakede.thesis.game.equipment.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class DefaultCaseTest {

    public static class DefaultCaseEqualsHashCodeTest extends EqualsHashCodeTestCase {
        public DefaultCaseEqualsHashCodeTest(String name) {
            super(name);
        }

        @Override
        protected DefaultCase createInstance() throws Exception {
            return new DefaultCase(new DefaultSuspicion(Suspect.GREEN, Room.BILLIARD_ROOM, Weapon.KNIFE));
        }

        @Override
        protected DefaultCase createNotEqualInstance() throws Exception {
            return new DefaultCase(new DefaultSuspicion(Suspect.WHITE, Room.BILLIARD_ROOM, Weapon.KNIFE));
        }
    }

    public static class DefaultCaseAccuseTest {
        private Suspicion suspicion;
        private Case testSubject;

        @Before
        public void setUp() throws Exception {
            suspicion = new DefaultSuspicion(Suspect.GREEN, Room.BILLIARD_ROOM, Weapon.CANDLESTICK);
            testSubject = new DefaultCase(suspicion);
        }

        @Test
        public void testAccuseShouldReturnWithTheSameCaseWhenTheSuspicionWasIncorrect() throws Exception {
            Suspicion currentSuspicion = new DefaultSuspicion(Suspect.GREEN, Room.BILLIARD_ROOM, Weapon.KNIFE);
            Case actual = testSubject.accuse(currentSuspicion);
            assertEquals(testSubject, actual);
        }

        @Test
        public void testAccuseShouldReturnWithTheANewCaseWhenTheSuspicionWasCorrect() throws Exception {
            Case actual = testSubject.accuse(suspicion);
            assertNotEquals(testSubject, actual);
        }

        @Test
        public void testAccuseShouldReturnWithTheANewSolvedCaseWhenTheSuspicionWasCorrect() throws Exception {
            Case actual = testSubject.accuse(suspicion);
            assertNotEquals(testSubject, actual);
            assertTrue(actual.isSolved());
        }

        @Test
        public void testEqualsShouldReturnFalseWhenTypeIsDifferent() {
            Case actual = testSubject.accuse(suspicion);
            assertFalse(actual.equals(suspicion));
        }

    }
}