package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.Player;
import me.belakede.thesis.internal.game.PlayerCycle;

import java.util.ArrayList;
import java.util.List;

class DefaultPlayerCycle implements PlayerCycle {

    private final List<Player> players;
    private int index;

    DefaultPlayerCycle(Player first) {
        players = new ArrayList<>(6);
        players.add(first);
        index = 0;
    }

    @Override
    public void first() {
        index = 0;
    }

    @Override
    public void next() {
        index = getNextIndex();
    }

    @Override
    public int size() {
        return players.size();
    }

    @Override
    public int getNumberOfPlayers() {
        return (int) players.stream().filter(player -> !player.hasBeenMadeGroundlessAccusation()).count();
    }

    @Override
    public boolean hasNext() {
        return getNumberOfPlayers() > 1;
    }

    @Override
    public void append(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    @Override
    public Player getCurrent() {
        return players.get(index);
    }

    @Override
    public Player getNext() {
        return players.get(getNextIndex());
    }

    private int getNextIndex() {
        return (index + 1 == players.size()) ? 0 : index + 1;
    }

}
