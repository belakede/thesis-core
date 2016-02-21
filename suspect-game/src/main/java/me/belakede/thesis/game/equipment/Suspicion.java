package me.belakede.thesis.game.equipment;

import java.io.Serializable;

public interface Suspicion extends Serializable {

    Suspect getSuspect();

    Room getRoom();

    Weapon getWeapon();

}
