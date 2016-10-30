package me.belakede.thesis.internal.game.equipment;

import junitx.extensions.EqualsHashCodeTestCase;
import me.belakede.thesis.game.equipment.Room;
import me.belakede.thesis.game.equipment.Suspect;
import me.belakede.thesis.game.equipment.Suspicion;
import me.belakede.thesis.game.equipment.Weapon;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class DefaultSuspicionTest {

    public static final class DefaultSuspicionEqualsHashCodeTest extends EqualsHashCodeTestCase {

        public DefaultSuspicionEqualsHashCodeTest(String name) {
            super(name);
        }

        @Override
        protected Suspicion createInstance() throws Exception {
            return new DefaultSuspicion(Suspect.GREEN, Room.BILLIARD_ROOM, Weapon.CANDLESTICK);
        }

        @Override
        protected Suspicion createNotEqualInstance() throws Exception {
            return new DefaultSuspicion(Suspect.WHITE, Room.BILLIARD_ROOM, Weapon.CANDLESTICK);
        }
    }

}