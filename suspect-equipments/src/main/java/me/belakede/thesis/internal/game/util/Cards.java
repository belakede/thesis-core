package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.equipment.*;

import java.util.*;

public class Cards {

    private static final EnumSet<Room> ROOM_CARDS;
    private static final EnumSet<Suspect> SUSPECT_CARDS;
    private static final EnumSet<Weapon> WEAPON_CARDS;
    private static final Set<Card> ALL_CARDS;
    private static final Random RANDOM;
    private static final Map<String, String> NAME_CACHE;

    static {
        ROOM_CARDS = EnumSet.allOf(Room.class);
        SUSPECT_CARDS = EnumSet.allOf(Suspect.class);
        WEAPON_CARDS = EnumSet.allOf(Weapon.class);
        ALL_CARDS = initCards();
        RANDOM = new Random();
        NAME_CACHE = new HashMap<>(ALL_CARDS.size());
    }

    private Cards() {
    }

    public static Optional<Card> valueOf(String name) {
        return (name == null)
                ? Optional.empty()
                : ALL_CARDS.stream().filter(card -> card.toString().equals(getCachedName(name))).findFirst();
    }

    public static Set<Card> values() {
        return Collections.unmodifiableSet(ALL_CARDS);
    }

    public static int getNumberOfCard() {
        return ALL_CARDS.size();
    }

    public static List<Card> shuffledValuesExcept(Case mystery) {
        if (mystery == null) {
            throw new NullPointerException();
        }
        List<Card> filteredCards = new ArrayList<>(ALL_CARDS);
        removeFromList(filteredCards, mystery);
        shuffle(filteredCards);
        return Collections.unmodifiableList(filteredCards);
    }

    public static Optional<Room> getRandomRoom() {
        return ROOM_CARDS.stream().skip(RANDOM.nextInt(ROOM_CARDS.size())).findFirst();
    }

    public static Optional<Suspect> getRandomSuspect() {
        return SUSPECT_CARDS.stream().skip(RANDOM.nextInt(SUSPECT_CARDS.size())).findFirst();
    }

    public static Optional<Weapon> getRandomWeapon() {
        return WEAPON_CARDS.stream().skip(RANDOM.nextInt(WEAPON_CARDS.size())).findFirst();
    }

    private static Set<Card> initCards() {
        Set<Card> cards = new HashSet<>();
        cards.addAll(ROOM_CARDS);
        cards.addAll(SUSPECT_CARDS);
        cards.addAll(WEAPON_CARDS);
        return cards;
    }

    private static void removeFromList(List<Card> list, Case mystery) {
        list.remove(mystery.getRoom());
        list.remove(mystery.getSuspect());
        list.remove(mystery.getWeapon());
    }

    private static void shuffle(List<Card> list) {
        for (int i = 0; i < RANDOM.nextInt(10); i++) {
            Collections.shuffle(list);
        }
    }

    private static String getCachedName(String name) {
        if (!NAME_CACHE.containsKey(name)) {
            NAME_CACHE.put(name, name.trim().toUpperCase());
        }
        return NAME_CACHE.get(name);
    }

}
