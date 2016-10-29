package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.Player;
import me.belakede.thesis.internal.game.PlayerCycle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Players {

    private Players() {
    }

    public static PlayerCycle createPlayerCycle(Player... players) {
        return createPlayerCycle(Arrays.asList(players));
    }

    public static PlayerCycle createOrderedPlayerCycle(Player... players) {
        return createOrderedPlayerCycle(Arrays.asList(players));
    }

    public static PlayerCycle createPlayerCycle(List<Player> players) {
        List<Player> playerList = new ArrayList<>(players);
        Collections.shuffle(playerList);
        DefaultPlayerCycle playerCycle = new DefaultPlayerCycle(playerList.remove(0));
        while (!playerList.isEmpty()) {
            Collections.shuffle(playerList);
            playerCycle.append(playerList.remove(0));
        }
        return playerCycle;
    }

    public static PlayerCycle createOrderedPlayerCycle(List<Player> players) {
        List<Player> playerList = new ArrayList<>(players);
        DefaultPlayerCycle playerCycle = new DefaultPlayerCycle(playerList.remove(0));
        while (!playerList.isEmpty()) {
            playerCycle.append(playerList.remove(0));
        }
        return playerCycle;
    }

}
