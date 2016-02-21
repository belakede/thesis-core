package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.equipment.*;

import java.util.*;

public class Cards {

    private Cards() {}

    private static final EnumSet<Room> ROOMS;

    private static final EnumSet<Suspect> SUSPECTS;

    private static final EnumSet<Weapon> WEAPONS;

    private static final Set<Card> CARDS;

    private static final Random RANDOM;

    private static final Map<String, String> NAME_CACHE;

    static {
        ROOMS = EnumSet.allOf(Room.class);
        SUSPECTS = EnumSet.allOf(Suspect.class);
        WEAPONS = EnumSet.allOf(Weapon.class);
        CARDS = initCards();
        RANDOM = new Random();
        NAME_CACHE = new HashMap<>(CARDS.size());
    }

    public static Optional<Card> valueOf(String name) {
        return (name == null)
                ? Optional.empty()
                : CARDS.stream().filter(card -> card.toString().equals(getCachedName(name))).findFirst();
    }

    public static Set<Card> values() {
        return Collections.unmodifiableSet(CARDS);
    }

    public static List<Card> shuffledValuesExcept(Case mystery) {
        if (mystery == null) throw new NullPointerException();
        List<Card> filteredCards = new ArrayList<>(CARDS);
        removeFromList(filteredCards, mystery);
        shuffle(filteredCards);
        return Collections.unmodifiableList(filteredCards);
    }

    public static Optional<Room> getRandomRoom() {
        return ROOMS.stream().skip(RANDOM.nextInt(ROOMS.size())).findFirst();
    }

    public static Optional<Suspect> getRandomSuspect() {
        return SUSPECTS.stream().skip(RANDOM.nextInt(SUSPECTS.size())).findFirst();
    }

    public static Optional<Weapon> getRandomWeapon() {
        return WEAPONS.stream().skip(RANDOM.nextInt(WEAPONS.size())).findFirst();
    }

    private static Set<Card> initCards() {
        Set<Card> cards = new HashSet<>();
        cards.addAll(ROOMS);
        cards.addAll(SUSPECTS);
        cards.addAll(WEAPONS);
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
