package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.board.Board;
import me.belakede.thesis.game.board.RoomField;
import me.belakede.thesis.game.board.SecretPassage;
import me.belakede.thesis.game.equipment.BoardType;
import me.belakede.thesis.game.field.Field;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class DefaultBoard implements Board {

    private final BoardType type;
    private final Set<RoomField> roomFields;
    private final Set<SecretPassage> secretPassages;
    private final FieldMatrix fieldMatrix;

    public DefaultBoard(BoardType type, Field[][] fields, Set<RoomField> roomFields, Set<SecretPassage> secretPassages) {
        this.type = type;
        this.fieldMatrix = new FieldMatrix(fields.length, fields);
        this.roomFields = new HashSet<>(roomFields);
        this.secretPassages = new HashSet<>(secretPassages);
    }

    public DefaultBoard(BoardType type, FieldMatrix fieldMatrix, Set<RoomField> roomFields, Set<SecretPassage> secretPassages) {
        this.type = type;
        this.fieldMatrix = new FieldMatrix(fieldMatrix);
        this.roomFields = new HashSet<>(roomFields);
        this.secretPassages = new HashSet<>(secretPassages);
    }

    @Override
    public BoardType getBoardType() {
        return type;
    }

    @Override
    public Field getField(int row, int column) {
        return fieldMatrix.getField(row, column);
    }

    @Override
    public Set<Field> getStartingFields() {
        return Collections.unmodifiableSet(fieldMatrix.getStartingFields());
    }

    @Override
    public Set<RoomField> getRoomFields() {
        return Collections.unmodifiableSet(roomFields);
    }

    @Override
    public boolean isAvailable(Field from, Field to) {
        Set<Field> fromFields = getAllFields(from);
        Set<Field> toFields = getAllFields(to);
        return isAvailableSomehow(fromFields, toFields) || hasSecretPassage(from, to);
    }

    @Override
    public Set<Field> availableFields(Field from, int step) {
        Set<Field> fromFields = getAllFields(from);
        Set<Field> availableFields = new HashSet<>();
        availableFields.addAll(getRoomFields(from));
        fromFields.stream().map(f -> fieldMatrix.getAvailableFields(from, step)).forEach(availableFields::addAll);
        return Collections.unmodifiableSet(availableFields);
    }

    private Set<Field> getAllFields(Field from) {
        Set<Field> fields = new HashSet<>();
        Optional<RoomField> roomField = findRoomFieldByField(from);
        if (roomField.isPresent()) {
            fields.addAll(roomField.get().getExitFields());
        } else {
            fields.add(from);
        }
        return fields;
    }

    private boolean isAvailableSomehow(Set<Field> fromFields, Set<Field> toFields) {
        return toFields.stream().filter(t -> isAvailable(fromFields, t)).findFirst().isPresent();
    }

    private boolean isAvailable(Set<Field> fromFields, Field to) {
        return fromFields.stream().filter(from -> fieldMatrix.isAvailable(from, to)).findFirst().isPresent();
    }

    private boolean hasSecretPassage(Field from, Field to) {
        Optional<RoomField> fromRoomField = findRoomFieldByField(from);
        Optional<RoomField> toRoomField = findRoomFieldByField(to);
        return fromRoomField.isPresent() && toRoomField.isPresent() && hasSecretPassage(fromRoomField.get(), toRoomField.get());
    }

    private boolean hasSecretPassage(RoomField from, RoomField to) {
        return secretPassages
                .stream()
                .filter(sp -> sp.isPartOfSecretPassage(from) && sp.isPartOfSecretPassage(to))
                .findAny()
                .isPresent();
    }

    private Optional<RoomField> findRoomFieldByField(Field field) {
        return roomFields.stream().filter(rf -> rf.isPartOfRoom(field)).findFirst();
    }

    private Set<Field> getRoomFields(Field from) {
        Set<Field> fields = new HashSet<>();
        Optional<RoomField> roomField = findRoomFieldByField(from);
        if (roomField.isPresent()) {
            fields.addAll(roomField.get().getFields());
            fields.addAll(getSecretRoomFields(roomField.get()));
        }
        return fields;
    }

    private Set<Field> getSecretRoomFields(RoomField roomField) {
        Set<Field> fields = new HashSet<>();
        Optional<SecretPassage> secretPassage = secretPassages.stream()
                .filter(sp -> sp.isPartOfSecretPassage(roomField))
                .findFirst();
        if (secretPassage.isPresent()) {
            Set<RoomField> result = roomFields.stream()
                    .filter(secretPassage.get()::isPartOfSecretPassage)
                    .collect(Collectors.toSet());
            result.remove(roomField);
            result.forEach(rf -> fields.addAll(rf.getFields()));
        }
        return fields;
    }

}
