package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.board.Board;
import me.belakede.thesis.game.equipment.*;
import me.belakede.thesis.game.field.Field;

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

    public static Optional<Figurine> valueOf(String name) {
        return ALL_FIGURINES.stream().filter(figurine -> figurine.name().equalsIgnoreCase(name)).findFirst();
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
        while (!cards.isEmpty() && cards.size() >= numberOfPlayers) {
            Collections.shuffle(cards);
            playersCards.values().forEach(c -> c.add(cards.remove(0)));
        }
        List<Set<Card>> cardList = new ArrayList<>(playersCards.values());
        int sizeOfCards = cards.size();
        for (int i = 0; i < sizeOfCards; i++) {
            cardList.get(i).add(cards.remove(0));
        }
        return playersCards;
    }


    public static Map<Figurine, Field> startingPositions(Board board) {
        Map<Figurine, Field> figurineFieldMap = new HashMap<>(getNumberOfFigurines());
        addSuspectsToPositionMap(board, figurineFieldMap);
        addWeaponsToPositionMap(board, figurineFieldMap);
        return figurineFieldMap;
    }

    private static void addWeaponsToPositionMap(Board board, Map<Figurine, Field> figurineFieldMap) {
        List<Field> roomFields = new ArrayList<>();
        board.getRoomFields().forEach(rf -> roomFields.addAll(rf.getFields()));
        board.getRoomFields().forEach(rf -> roomFields.removeAll(rf.getExitFields()));
        List<Weapon> weapons = new ArrayList<>(WEAPON_FIGURINES);
        while (!weapons.isEmpty()) {
            Collections.shuffle(roomFields);
            figurineFieldMap.put(weapons.remove(0), roomFields.remove(0));
        }
    }

    private static void addSuspectsToPositionMap(Board board, Map<Figurine, Field> figurineFieldMap) {
        List<Field> startingFields = new ArrayList<>(board.getStartingFields());
        List<Suspect> suspects = new ArrayList<>(SUSPECT_FIGURINES);
        while (!suspects.isEmpty() && !startingFields.isEmpty()) {
            Collections.shuffle(suspects);
            Collections.shuffle(startingFields);
            figurineFieldMap.put(suspects.remove(0), startingFields.remove(0));
        }
    }

    private static Map<Figurine, Set<Card>> getFigurinesWithEmptyCardSet(int numberOfPlayers) {
        List<Figurine> figurines = new ArrayList<>(SUSPECT_FIGURINES);
        Collections.shuffle(figurines);
        Map<Figurine, Set<Card>> playersCards = new HashMap<>(numberOfPlayers);
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
