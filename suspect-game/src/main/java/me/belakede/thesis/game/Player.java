package me.belakede.thesis.game;

import me.belakede.thesis.game.equipment.Card;
import me.belakede.thesis.game.equipment.Suspect;

import java.util.Set;

public interface Player {

    void makeGroundlessAccusation();

    boolean hasBeenMadeGroundlessAccusation();

    Set<Card> getCards();

    boolean hasCard(Card card);

    Suspect getFigurine();

}
