package me.belakede.thesis.internal.game.equipment;


import me.belakede.thesis.game.equipment.Room;
import me.belakede.thesis.game.equipment.Suspect;
import me.belakede.thesis.game.equipment.Suspicion;
import me.belakede.thesis.game.equipment.Weapon;

public final class DefaultSuspicion implements Suspicion {

    private final Suspect suspect;
    private final Room room;
    private final Weapon weapon;

    public DefaultSuspicion(Suspect suspect, Room room, Weapon weapon) {
        this.suspect = suspect;
        this.room = room;
        this.weapon = weapon;
    }

    @Override
    public Suspect getSuspect() {
        return suspect;
    }

    @Override
    public Room getRoom() {
        return room;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultSuspicion that = (DefaultSuspicion) o;

        if (suspect != that.suspect) return false;
        if (room != that.room) return false;
        return weapon == that.weapon;

    }

    @Override
    public int hashCode() {
        int result = suspect.hashCode();
        result = 31 * result + room.hashCode();
        result = 31 * result + weapon.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Suspicion{" +
                "suspect=" + suspect +
                ", room=" + room +
                ", weapon=" + weapon +
                '}';
    }
}
