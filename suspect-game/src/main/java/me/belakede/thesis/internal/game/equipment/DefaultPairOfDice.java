package me.belakede.thesis.internal.game.equipment;

import me.belakede.thesis.game.equipment.PairOfDice;

import java.util.concurrent.ThreadLocalRandom;


public final class DefaultPairOfDice implements PairOfDice {
    
    private final int first;
    private final int second;

    private DefaultPairOfDice(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int getFirst() {
        return first;
    }

    @Override
    public int getSecond() {
        return second;
    }

    @Override
    public int getResult() {
        return first + second;
    }

    @Override
    public PairOfDice roll() {
        return create();
    }
    
    public static final PairOfDice create() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new DefaultPairOfDice(random.nextInt(6) + 1, random.nextInt(6) + 1);
    }
    
}
