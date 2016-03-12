package me.belakede.thesis.internal.game;


import me.belakede.thesis.game.Player;
import me.belakede.thesis.game.equipment.Card;
import me.belakede.thesis.game.equipment.Suspect;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class DefaultPlayer implements Player {

    private final Set<Card> cards;
    private final Suspect figurine;
    private boolean madeGroundlessAccusation;

    public DefaultPlayer(Suspect figurine, Collection<Card> cards) {
        this.figurine = figurine;
        this.cards = new HashSet<>(cards);
        this.madeGroundlessAccusation = false;
    }

    @Override
    public void makeGroundlessAccusation() {
        madeGroundlessAccusation = true;
    }

    @Override
    public boolean hasBeenMadeGroundlessAccusation() {
        return madeGroundlessAccusation;
    }

    @Override
    public Set<Card> getCards() {
        return new HashSet<>(cards);
    }

    @Override
    public boolean hasCard(Card card) {
        return cards.contains(card);
    }

    @Override
    public Suspect getFigurine() {
        return figurine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DefaultPlayer that = (DefaultPlayer) o;

        return cards.equals(that.cards) && figurine == that.figurine;
    }

    @Override
    public int hashCode() {
        int result = cards.hashCode();
        result = 31 * result + figurine.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DefaultPlayer{" +
                "figurine=" + figurine +
                ", madeGroundlessAccusation=" + madeGroundlessAccusation +
                '}';
    }
}
