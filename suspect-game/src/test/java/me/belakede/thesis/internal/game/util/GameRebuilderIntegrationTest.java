package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.Game;
import me.belakede.thesis.game.Player;
import me.belakede.thesis.game.board.BoardType;
import me.belakede.thesis.game.equipment.*;
import me.belakede.thesis.internal.game.DefaultPlayer;
import me.belakede.thesis.internal.game.equipment.DefaultCase;
import me.belakede.thesis.internal.game.equipment.DefaultSuspicion;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class GameRebuilderIntegrationTest {

    @Test
    public void testCanCreateAValidDefaultGameWithFourPlayer() throws IOException {
        Case mystery = new DefaultCase(new DefaultSuspicion(Suspect.PEACOCK, Room.DINING_ROOM, Weapon.WRENCH));
        List<Coordinate> coordinates = new ArrayList<>(Arrays.asList(new Coordinate(2, 2),
                new Coordinate(6, 6), new Coordinate(10, 10), new Coordinate(23, 3)));
        List<Player> playerList = new ArrayList<>();
        Map<Figurine, Coordinate> knownPositions = new HashMap<>();
        Map<Figurine, Set<Card>> figurinesCards = Figurines.cards(4, mystery);
        figurinesCards.entrySet().forEach(e -> {
            playerList.add(new DefaultPlayer((Suspect) e.getKey(), e.getValue()));
            knownPositions.put(e.getKey(), coordinates.get(0));
            coordinates.remove(0);
        });
        Player currentPlayer = playerList.get(2);
        Game actual = GameRebuilder.create()
                .boardType(BoardType.DEFAULT)
                .mystery(mystery)
                .players(playerList, currentPlayer)
                .positions(knownPositions)
                .build();

        assertThat(actual.getBoard().getBoardType(), is(BoardType.DEFAULT));
        assertThat(actual.getPositions().size(), is(Figurines.getNumberOfFigurines()));
        assertThat(actual.getCurrentPlayer(), is(currentPlayer));
        knownPositions.keySet().forEach(f -> {
            assertThat(actual.getPositions().get(f).getColumn(), is(knownPositions.get(f).getColumn()));
            assertThat(actual.getPositions().get(f).getRow(), is(knownPositions.get(f).getRow()));
        });
    }

}