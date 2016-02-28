package me.belakede.thesis.game.board;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface Board extends Serializable {

    BoardType getBoardType();

    Set<Field> getStartingFields();

    List<Field> getRoomFields();

    boolean isAvailable(Field from, Field to);

    Set<Field> availableFields(Field from, int step);

}
