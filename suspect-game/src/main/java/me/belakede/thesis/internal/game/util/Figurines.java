package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.equipment.Figurine;
import me.belakede.thesis.game.equipment.Suspect;
import me.belakede.thesis.game.equipment.Weapon;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class Figurines {

    private static final EnumSet<Suspect> SUSPECT_FIGURINES;
    private static final EnumSet<Weapon> WEAPON_FIGURINES;
    private static final Set<Figurine> ALL_FIGURINES;

    static {
        SUSPECT_FIGURINES = EnumSet.allOf(Suspect.class);
        WEAPON_FIGURINES = EnumSet.allOf(Weapon.class);
        ALL_FIGURINES = initFigurines();
    }

    private Figurines() {
    }

    public static int getNumberOfFigurines() {
        return ALL_FIGURINES.size();
    }

    public static Set<Figurine> values() {
        return Collections.unmodifiableSet(ALL_FIGURINES);
    }

    private static Set<Figurine> initFigurines() {
        Set<Figurine> cards = new HashSet<>();
        cards.addAll(SUSPECT_FIGURINES);
        cards.addAll(WEAPON_FIGURINES);
        return cards;
    }

}
