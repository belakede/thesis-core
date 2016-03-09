package me.belakede.thesis.internal.game;

import me.belakede.thesis.game.Player;

public interface PlayerCycle {

    void first();

    void next();

    int size();

    int getNumberOfPlayers();

    boolean hasNext();

    void append(Player player);

    Player getCurrent();

    Player getNext();

}
