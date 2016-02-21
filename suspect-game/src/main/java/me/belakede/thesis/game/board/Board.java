package me.belakede.thesis.game.board;

import java.io.Serializable;
import java.util.Set;

public interface Board extends Serializable {

    BoardType getBoardType();

    Set<Field> getStartingFields();

    Set<Field> getRoomFields();

    boolean isAvailable(Field from, Field to);

    Set<Field> availableFields(Field from, int step);

}
