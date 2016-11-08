package me.belakede.thesis.internal.game.importer.transformer;


import me.belakede.thesis.game.board.RoomField;
import me.belakede.thesis.game.board.SecretPassage;
import me.belakede.thesis.game.equipment.Room;
import me.belakede.thesis.game.field.Field;
import me.belakede.thesis.internal.game.board.DefaultRoomField;
import me.belakede.thesis.internal.game.board.DefaultSecretPassage;
import me.belakede.thesis.internal.game.importer.domain.RoomDescription;
import me.belakede.thesis.internal.game.importer.domain.RoomFieldDescriptor;
import me.belakede.thesis.internal.game.importer.domain.SecretPassageDescriptor;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class DescriptorTransformer {

    private final Field[][] fields;
    private final RoomDescription roomDescription;
    private final Set<RoomField> roomFields;
    private final Set<SecretPassage> secretPassages;

    public DescriptorTransformer(Field[][] fields, RoomDescription roomDescription) {
        this.fields = fields;
        this.roomDescription = roomDescription;
        this.roomFields = new HashSet<>();
        this.secretPassages = new HashSet<>();
        transform();
    }

    public Set<RoomField> getRoomFields() {
        return roomFields;
    }

    public Set<SecretPassage> getSecretPassages() {
        return secretPassages;
    }

    private void transform() {
        transformRoomDescriptors();
        transformSecretPassageDescriptors();
    }

    private void transformRoomDescriptors() {
        roomDescription.getRoomFields().stream()
                .map(this::transformRoomDescriptor)
                .forEach(roomFields::add);
    }

    private void transformSecretPassageDescriptors() {
        roomDescription.getSecretPassages().stream()
                .map(this::transformSecretPassageDescriptor)
                .forEach(secretPassages::add);
    }

    private RoomField transformRoomDescriptor(RoomFieldDescriptor descriptor) {
        Set<Field> actualFields = descriptor.getFields().stream().map(this::transformField).collect(Collectors.toSet());
        Set<Field> exitFields = descriptor.getExitFields().stream().map(this::transformField).collect(Collectors.toSet());
        return new DefaultRoomField(descriptor.getRoom(), actualFields, exitFields);
    }

    private SecretPassage transformSecretPassageDescriptor(SecretPassageDescriptor descriptor) {
        Optional<RoomField> fromRoomField = getRoomFieldByRoom(descriptor.getFromField());
        Optional<RoomField> toRoomField = getRoomFieldByRoom(descriptor.getToField());
        if (fromRoomField.isPresent() && toRoomField.isPresent()) {
            return new DefaultSecretPassage(fromRoomField.get(), toRoomField.get());
        }
        throw new IllegalArgumentException("Room not found on board!");
    }

    private Field transformField(List<Integer> coords) {
        return fields[coords.get(0)][coords.get(1)];
    }

    private Optional<RoomField> getRoomFieldByRoom(Room room) {
        return roomFields.stream().filter(rf -> room.equals(rf.getRoom())).findFirst();
    }


}
