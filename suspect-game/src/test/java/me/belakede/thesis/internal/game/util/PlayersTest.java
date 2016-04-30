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
        Player firstPlayer = Mockito.mock(Player.class);
        Player secondPlayer = Mockito.mock(Player.class);
        Player thirdPlayer = Mockito.mock(Player.class);
        Player fourthPlayer = Mockito.mock(Player.class);
        // WHEN
        PlayerCycle actual = Players.createPlayerCycle(firstPlayer, secondPlayer, thirdPlayer, fourthPlayer);
        assertEquals(4, actual.getNumberOfPlayers());
    }

    @Test
    public void testCreatePlayerCycleShouldReturnWithAPlayerCycleWhichContainsAllSpecifiedPlayer() {
        // GIVEN
        Player firstPlayer = Mockito.mock(Player.class);
        Player secondPlayer = Mockito.mock(Player.class);
        Player thirdPlayer = Mockito.mock(Player.class);
        Player fourthPlayer = Mockito.mock(Player.class);
        List<Player> players = Arrays.asList(firstPlayer, secondPlayer, thirdPlayer, fourthPlayer);
        // WHEN
        PlayerCycle actual = Players.createPlayerCycle(players);
        // THEN
        Player first = actual.getCurrent();
        do {
            assertTrue(players.contains(actual.getCurrent()));
            actual.next();
        } while (!first.equals(actual.getCurrent()));
    }

}