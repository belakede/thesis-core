package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.board.FieldType;
import me.belakede.thesis.game.board.RoomField;
import me.belakede.thesis.game.equipment.Room;

public final class FieldFactory {

    private static final Field NULL_FIELD;

    static {
        NULL_FIELD = new DefaultField(FieldType.NULL, -1, -1);
    }

    private FieldFactory() {
    }

    public static Field getNullField() {
        return NULL_FIELD;
    }

    public static RoomField getRoomField(Room room, int row, int column) {
        return new DefaultRoomField(room, row, column);
    }

    public static RoomField getRoomFieldWithSecretPassage(Room room, Room secretRoom, int row, int column) {
        return new DefaultRoomField(room, secretRoom, row, column);
    }

    public static Field getWall(int row, int column) {
        return new DefaultField(FieldType.WALL, row, column);
    }

    public static Field getSimpleField(int row, int column) {
        return new DefaultField(FieldType.SIMPLE, row, column);
    }

    public static Field getStartField(int row, int column) {
        return new DefaultField(FieldType.START, row, column);
    }

    public static Field getEndField(int row, int column) {
        return new DefaultField(FieldType.END, row, column);
    }

}
