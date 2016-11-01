package me.belakede.thesis.game;

import me.belakede.thesis.game.board.Board;
import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.equipment.Case;
import me.belakede.thesis.game.equipment.Figurine;
import me.belakede.thesis.game.equipment.PairOfDice;
import me.belakede.thesis.game.equipment.Suspicion;

import java.util.Map;

public interface Game {

    Board getBoard();

    Case getMystery();

    Map<Figurine, Field> getPositions();

    PairOfDice roll();

    Player getCurrentPlayer();

    Player getNextPlayer();

    void move(Field field);

    void suspect(Suspicion suspicion);

    void accuse(Suspicion suspicion);

    void next();

    boolean isGameEnded();
}
