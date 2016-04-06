package me.belakede.thesis.internal.game.importer.domain;

import me.belakede.thesis.game.equipment.Room;

public class SecretPassageDescriptor {

    private Room fromField;
    private Room toField;

    public SecretPassageDescriptor() {
    }

    public SecretPassageDescriptor(Room fromField, Room toField) {
        this.fromField = fromField;
        this.toField = toField;
    }

    public Room getFromField() {
        return fromField;
    }

    public void setFromField(Room fromField) {
        this.fromField = fromField;
    }

    public Room getToField() {
        return toField;
    }

    public void setToField(Room toField) {
        this.toField = toField;
    }

    @Override
    public String toString() {
        return "SecretPassageDescriptor{" +
                "fromField=" + fromField +
                ", toField=" + toField +
                '}';
    }
}
