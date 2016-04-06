package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.board.RoomField;
import me.belakede.thesis.game.equipment.Room;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DefaultRoomField implements RoomField {

    private final Room room;
    private final Set<Field> fields;
    private final Set<Field> exitFields;

    public DefaultRoomField(Room room, Collection<Field> fields, Collection<Field> exitFields) {
        this.room = room;
        this.fields = new HashSet<>(fields);
        this.exitFields = new HashSet<>(exitFields);
        this.fields.addAll(exitFields);
    }

    @Override
    public Room getRoom() {
        return room;
    }

    @Override
    public Set<Field> getFields() {
        return Collections.unmodifiableSet(fields);
    }

    @Override
    public Set<Field> getExitFields() {
        return Collections.unmodifiableSet(exitFields);
    }

    @Override
    public boolean isPartOfRoom(Field field) {
        return fields.contains(field);
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

        if (room != that.room) {
            return false;
        }
        if (fields != null ? !fields.equals(that.fields) : that.fields != null) {
            return false;
        }
        return exitFields != null ? exitFields.equals(that.exitFields) : that.exitFields == null;

    }

    @Override
    public int hashCode() {
        return room.hashCode();
    }

    @Override
    public String toString() {
        return "RoomField{" +
                "room=" + room +
                ", fields=" + fields +
                ", exitFields=" + exitFields +
                '}';
    }
}
