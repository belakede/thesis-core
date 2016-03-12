package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.board.FieldType;
import me.belakede.thesis.game.board.RoomField;
import me.belakede.thesis.game.equipment.Room;

import java.util.Optional;

public class DefaultRoomField implements RoomField {

    private final int row;
    private final int column;
    private final Room room;
    private final Room secretRoom;

    DefaultRoomField(Room room, int row, int column) {
        this(room, null, row, column);
    }

    DefaultRoomField(Room room, Room secretRoom, int row, int column) {
        this.room = room;
        this.row = row;
        this.column = column;
        this.secretRoom = secretRoom;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public Room getRoom() {
        return room;
    }

    @Override
    public Optional<Room> getSecretRoom() {
        return Optional.of(room);
    }

    public FieldType getFieldType() {
        return FieldType.ROOM;
    }

    @Override
    public boolean canMakeAnAccusation() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DefaultRoomField that = (DefaultRoomField) o;

        return !(row != that.row || column != that.column || room != that.room)
                && (secretRoom != null ? secretRoom.equals(that.secretRoom) : that.secretRoom == null);

    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        result = 31 * result + room.hashCode();
        result = 31 * result + (secretRoom != null ? secretRoom.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoomField{" +
                "row: " + row +
                ", column: " + column +
                ", room: " + room +
                ", secretRoom: " + secretRoom +
                '}';
    }
}
