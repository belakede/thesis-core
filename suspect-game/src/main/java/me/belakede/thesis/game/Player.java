package me.belakede.thesis.game;

import me.belakede.thesis.game.equipment.Card;
import me.belakede.thesis.game.equipment.Suspect;

public interface Player {

    void makeGroundlessAccusation();

    boolean hasBeenMadeGroundlessAccusation();

    boolean hasCard(Card card);

    Suspect getFigurine();

}
