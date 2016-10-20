package me.belakede.thesis.game.equipment;

import java.io.Serializable;

public interface Case extends Serializable {

    Suspect getSuspect();

    Room getRoom();

    Weapon getWeapon();

    boolean isSolved();

    Case accuse(Suspicion suspicion);

}
