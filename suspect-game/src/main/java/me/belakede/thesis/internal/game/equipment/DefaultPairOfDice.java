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

    public static PairOfDice create() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new DefaultPairOfDice(random.nextInt(6) + 1, random.nextInt(6) + 1);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DefaultPairOfDice that = (DefaultPairOfDice) o;

        return first == that.first && second == that.second;
    }

    @Override
    public int hashCode() {
        int result = 73 * first;
        result = 73 * result + 73 * second;
        return result;
    }
}
