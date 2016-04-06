package me.belakede.thesis.internal.game.importer.domain;

import java.util.List;

public class RoomDescription {

    private List<RoomFieldDescriptor> roomFields;
    private List<SecretPassageDescriptor> secretPassages;

    public RoomDescription() {
    }

    public List<RoomFieldDescriptor> getRoomFields() {
        return roomFields;
    }

    public void setRoomFields(List<RoomFieldDescriptor> roomFields) {
        this.roomFields = roomFields;
    }

    public List<SecretPassageDescriptor> getSecretPassages() {
        return secretPassages;
    }

    public void setSecretPassages(List<SecretPassageDescriptor> secretPassages) {
        this.secretPassages = secretPassages;
    }

    @Override
    public String toString() {
        return "RoomDescription{" +
                "roomFields=" + roomFields +
                ", secretPassages=" + secretPassages +
                '}';
    }
}
