package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.board.FieldType;

public final class DefaultField implements Field {

    private final FieldType type;
    private final int row;
    private final int column;
    private final boolean accusationField;

    DefaultField(FieldType type, int row, int column, boolean accusationField) {
        this.type = type;
        this.row = row;
        this.column = column;
        this.accusationField = accusationField;
    }

    @Override
    public FieldType getType() {
        return type;
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
    public boolean canMakeAnAccusation() {
        return accusationField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DefaultField that = (DefaultField) o;

        if (row != that.row) {
            return false;
        }
        if (column != that.column) {
            return false;
        }
        return accusationField == that.accusationField && type == that.type;

    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + row;
        result = 31 * result + column;
        result = 31 * result + (accusationField ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Field{" +
                "type: " + type +
                ", row: " + row +
                ", column: " + column +
                ", accusationField: " + accusationField +
                '}';
    }
}
