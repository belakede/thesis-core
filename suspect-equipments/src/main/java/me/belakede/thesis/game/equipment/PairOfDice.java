package me.belakede.thesis.game.equipment;

public interface PairOfDice {

    int getFirst();

    int getSecond();

    int getResult();

    PairOfDice roll();

}
