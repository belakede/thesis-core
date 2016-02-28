package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.board.Board;
import me.belakede.thesis.game.board.BoardType;
import me.belakede.thesis.game.board.Field;

import java.util.List;
import java.util.Set;

public final class DefaultBoard implements Board {

    private final BoardType type;
    private final FieldMatrix fieldMatrix;

    public DefaultBoard(BoardType type, FieldMatrix fieldMatrix) {
        this.type = type;
        this.fieldMatrix = new FieldMatrix(fieldMatrix);
    }

    @Override
    public BoardType getBoardType() {
        return type;
    }

    @Override
    public Set<Field> getStartingFields() {
        return fieldMatrix.getStartingFields();
    }

    @Override
    public List<Field> getRoomFields() {
        return fieldMatrix.getRoomFields();
    }

    @Override
    public boolean isAvailable(Field from, Field to) {
        return fieldMatrix.isAvailable(from, to);
    }

    @Override
    public Set<Field> availableFields(Field from, int step) {
        return fieldMatrix.getAvailableFields(from, step);
    }
}
