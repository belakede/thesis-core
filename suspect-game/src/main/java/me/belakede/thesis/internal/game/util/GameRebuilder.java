package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.Game;
import me.belakede.thesis.game.Player;
import me.belakede.thesis.game.board.Board;
import me.belakede.thesis.game.board.BoardType;
import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.equipment.Case;
import me.belakede.thesis.game.equipment.Figurine;
import me.belakede.thesis.game.equipment.Suspicion;
import me.belakede.thesis.internal.game.DefaultGame;
import me.belakede.thesis.internal.game.PlayerCycle;
import me.belakede.thesis.internal.game.equipment.DefaultCase;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameRebuilder {

    public static BoardStep create() {
        return new GameSteps();
    }

    @FunctionalInterface
    public interface BoardStep {
        CaseStep boardType(BoardType boardType) throws IOException;
    }

    @FunctionalInterface
    public interface CaseStep {
        PlayersStep mystery(Suspicion suspicion);
    }

    @FunctionalInterface
    public interface PlayersStep {
        PositionsStep players(List<Player> orderedPlayers, Player currentPlayer);
    }

    @FunctionalInterface
    public interface PositionsStep {
        BuildStep positions(Map<Figurine, Coordinate> knownPositions);
    }

    @FunctionalInterface
    public interface BuildStep {
        Game build();
    }

    public static final class GameSteps implements BoardStep, CaseStep, PlayersStep, PositionsStep, BuildStep {

        private Board board;
        private Case mystery;
        private PlayerCycle players;
        private Map<Figurine, Field> positions;

        @Override
        public CaseStep boardType(BoardType boardType) throws IOException {
            board = Boards.getBoardByType(boardType);
            return this;
        }

        @Override
        public PlayersStep mystery(Suspicion suspicion) {
            mystery = new DefaultCase(suspicion);
            return this;
        }

        @Override
        public PositionsStep players(List<Player> orderedPlayers, Player currentPlayer) {
            players = Players.createOrderedPlayerCycle(orderedPlayers);
            while (!currentPlayer.equals(players.getCurrent())) {
                players.next();
            }
            return this;
        }

        @Override
        public BuildStep positions(Map<Figurine, Coordinate> knownPositions) {
            positions = Figurines.startingPositions(board);
            Set<Map.Entry<Figurine, Coordinate>> entries = new HashSet<>(knownPositions.entrySet());
            entries.forEach(e -> positions.put(e.getKey(), board.getField(e.getValue().getRow(), e.getValue().getColumn())));
            return this;
        }

        @Override
        public Game build() {
            return new DefaultGame(board, players, positions);
        }


    }

}
