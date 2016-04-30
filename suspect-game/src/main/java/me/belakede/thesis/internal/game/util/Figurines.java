package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.equipment.*;

import java.util.*;

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

    public static Map<Figurine, Set<Card>> cards(int numberOfPlayers, Case mystery) {
        Map<Figurine, Set<Card>> playersCards = getFigurinesWithEmptyCardSet(numberOfPlayers);
        List<Card> cards = new ArrayList<>(Cards.shuffledValuesExcept(mystery));
        while (!cards.isEmpty()) {
            Collections.shuffle(cards);
            playersCards.values().forEach(c -> c.add(cards.remove(0)));
        }
        return playersCards;
    }

    private static Map<Figurine, Set<Card>> getFigurinesWithEmptyCardSet(int numberOfPlayers) {
        List<Figurine> figurines = new ArrayList<>(ALL_FIGURINES);
        Collections.shuffle(figurines);
        Map<Figurine, Set<Card>> playersCards = new Hashtable<>(numberOfPlayers);
        for (int i = 0; i < numberOfPlayers; i++) {
            playersCards.put(figurines.get(i), new HashSet<>());
        }
        return playersCards;
    }

    private static Set<Figurine> initFigurines() {
        Set<Figurine> cards = new HashSet<>();
        cards.addAll(SUSPECT_FIGURINES);
        cards.addAll(WEAPON_FIGURINES);
        return cards;
    }

}
