package me.belakede.thesis.game.board;

import java.util.Set;

public interface Board {

    BoardType getBoardType();

    Set<Field> getStartingFields();

    Set<RoomField> getRoomFields();

    boolean isAvailable(Field from, Field to);

    Set<Field> availableFields(Field from, int step);

}
