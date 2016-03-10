package me.belakede.thesis.game;

import me.belakede.thesis.game.board.Board;
import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.equipment.PairOfDice;
import me.belakede.thesis.game.equipment.Suspicion;

public interface Game {

    Board getBoard();

    PairOfDice roll();

    void move(Field field);

    void suspect(Suspicion suspicion);

    void accuse(Suspicion suspicion);

}
