package me.belakede.thesis.internal.game.equipment;

import me.belakede.thesis.game.equipment.*;

public final class DefaultCase implements Case {

    private final Suspicion suspicion;
    private final boolean solved;

    public DefaultCase(Suspicion suspicion) {
        this(suspicion, false);
    }

    private DefaultCase(Suspicion suspicion, boolean solved) {
        this.suspicion = suspicion;
        this.solved = solved;
    }

    @Override
    public Suspect getSuspect() {
        return suspicion.getSuspect();
    }

    @Override
    public Room getRoom() {
        return suspicion.getRoom();
    }

    @Override
    public Weapon getWeapon() {
        return suspicion.getWeapon();
    }

    @Override
    public boolean isSolved() {
        return solved;
    }

    @Override
    public Case accuse(Suspicion currentSuspicion) {
        Case result = this;
        if (!solved && suspicion.equals(currentSuspicion)) {
            result = new DefaultCase(suspicion, true);
        }
        return result;
    }

}
