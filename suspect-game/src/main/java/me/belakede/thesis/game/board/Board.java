package me.belakede.thesis.game.board;

import me.belakede.thesis.game.equipment.BoardType;
import me.belakede.thesis.game.field.Field;

import java.util.Set;

public interface Board {

    BoardType getBoardType();

    Field getField(int row, int column);

    Set<Field> getStartingFields();

    Set<RoomField> getRoomFields();

    boolean isAvailable(Field from, Field to);

    Set<Field> availableFields(Field from, int step);

}
