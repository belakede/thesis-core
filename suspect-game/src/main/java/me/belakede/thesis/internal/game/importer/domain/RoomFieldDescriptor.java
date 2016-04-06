package me.belakede.thesis.internal.game.importer.domain;

import me.belakede.thesis.game.equipment.Room;

import java.util.List;

public class RoomFieldDescriptor {

    private Room room;
    private List<List<Integer>> fields;
    private List<List<Integer>> exitFields;

    public RoomFieldDescriptor() {
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<List<Integer>> getFields() {
        return fields;
    }

    public void setFields(List<List<Integer>> fields) {
        this.fields = fields;
    }

    public List<List<Integer>> getExitFields() {
        return exitFields;
    }

    public void setExitFields(List<List<Integer>> exitFields) {
        this.exitFields = exitFields;
    }

    @Override
    public String toString() {
        return "RoomFieldDescriptor{" +
                "room=" + room +
                ", fields=" + fields +
                ", exitFields=" + exitFields +
                '}';
    }
}
