package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.equipment.*;
import me.belakede.thesis.internal.game.equipment.DefaultCase;
import me.belakede.thesis.internal.game.equipment.DefaultSuspicion;

import java.util.Optional;
import java.util.function.Supplier;

public class Cases {

    private Cases() {
    }

    public static Case generate() {
        return new DefaultCase(getRandomSuspicion());
    }

    private static Suspicion getRandomSuspicion() {
        return new DefaultSuspicion(getRandomSuspect(), getRandomRoom(), getRandomWeapon());
    }

    private static Room getRandomRoom() {
        return getRandomCard(Cards::getRandomRoom);
    }

    private static Suspect getRandomSuspect() {
        return getRandomCard(Cards::getRandomSuspect);
    }

    private static Weapon getRandomWeapon() {
        return getRandomCard(Cards::getRandomWeapon);
    }

    private static <T> T getRandomCard(Supplier<Optional<T>> supplier) {
        T t = null;
        while (t == null) {
            Optional<T> randomT = supplier.get();
            if (randomT.isPresent()) {
                t = randomT.get();
            }
        }
        return t;
    }

}
