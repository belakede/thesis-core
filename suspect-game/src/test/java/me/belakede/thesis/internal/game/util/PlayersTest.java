package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.Player;
import me.belakede.thesis.internal.game.PlayerCycle;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayersTest {

    @Test
    public void testCreatePlayerCycleShouldReturnWithAFourElementPlayerCycleWhenCalledWithFourPlayer() {
        // GIVEN
        // WHEN
        PlayerCycle actual = Players.createPlayerCycle(Mockito.mock(Player.class),
                Mockito.mock(Player.class), Mockito.mock(Player.class), Mockito.mock(Player.class));
        assertEquals(4, actual.getNumberOfPlayers());
    }

    @Test
    public void testCreateOrderedPlayerCycleShouldReturnWithAFourElementPlayerCycleWhenCalledWithFourPlayer() {
        // GIVEN
        // WHEN
        PlayerCycle actual = Players.createOrderedPlayerCycle(Mockito.mock(Player.class),
                Mockito.mock(Player.class), Mockito.mock(Player.class), Mockito.mock(Player.class));
        assertEquals(4, actual.getNumberOfPlayers());
    }

    @Test
    public void testCreatePlayerCycleShouldReturnWithAPlayerCycleWhichContainsAllSpecifiedPlayer() {
        // GIVEN
        List<Player> players = Arrays.asList(Mockito.mock(Player.class),
                Mockito.mock(Player.class), Mockito.mock(Player.class), Mockito.mock(Player.class));
        // WHEN
        PlayerCycle actual = Players.createPlayerCycle(players);
        // THEN
        Player first = actual.getCurrent();
        do {
            assertTrue(players.contains(actual.getCurrent()));
            actual.next();
        } while (!first.equals(actual.getCurrent()));
    }

    @Test
    public void testCreateOrderedPlayerCycleShouldReturnWithAPlayerCycleWhichContainsAllSpecifiedPlayer() {
        // GIVEN
        List<Player> players = Arrays.asList(Mockito.mock(Player.class),
                Mockito.mock(Player.class), Mockito.mock(Player.class), Mockito.mock(Player.class));
        // WHEN
        PlayerCycle actual = Players.createOrderedPlayerCycle(players);
        // THEN
        int i = 0;
        Player first = actual.getCurrent();
        do {
            assertEquals(players.get(i), actual.getCurrent());
            actual.next();
            i++;
        } while (!first.equals(actual.getCurrent()));
    }

}