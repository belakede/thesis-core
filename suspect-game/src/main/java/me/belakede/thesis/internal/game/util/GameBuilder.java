package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.Game;
import me.belakede.thesis.game.Player;
import me.belakede.thesis.game.board.Board;
import me.belakede.thesis.game.board.BoardType;
import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.equipment.Card;
import me.belakede.thesis.game.equipment.Case;
import me.belakede.thesis.game.equipment.Figurine;
import me.belakede.thesis.game.equipment.Suspect;
import me.belakede.thesis.internal.game.DefaultGame;
import me.belakede.thesis.internal.game.DefaultPlayer;
import me.belakede.thesis.internal.game.PlayerCycle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameBuilder {

    private GameBuilder() {
    }

    public static BoardStep create() {
        return new GameSteps();
    }

    interface BoardStep {
        CaseStep boardType(BoardType boardType) throws IOException;
    }

    interface CaseStep {
        PlayersStep mystery();
    }

    interface PlayersStep {
        PositionsStep players(int numberOfPlayers);
    }

    interface PositionsStep {
        BuildStep positions();
    }

    interface BuildStep {
        Game build();
    }

    private static final class GameSteps implements BoardStep, CaseStep, PlayersStep, PositionsStep, BuildStep {

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
        public PlayersStep mystery() {
            mystery = Cases.generate();
            return this;
        }

        @Override
        public PositionsStep players(int numberOfPlayers) {
            Map<Figurine, Set<Card>> figurinesCards = Figurines.cards(numberOfPlayers, mystery);
            List<Player> playerList = new ArrayList<>();
            figurinesCards.entrySet().forEach(e -> playerList.add(new DefaultPlayer((Suspect) e.getKey(), e.getValue())));
            players = Players.createPlayerCycle(playerList);
            return this;
        }

        @Override
        public BuildStep positions() {
            positions = Figurines.startingPositions(board);
            return this;
        }

        @Override
        public Game build() {
            return new DefaultGame(board, mystery, players, positions);
        }

    }

}
