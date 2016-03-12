package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.board.FieldType;

public final class DefaultField implements Field {

    private final FieldType fieldType;
    private final int row;
    private final int column;

    DefaultField(FieldType fieldType, int row, int column) {
        this.fieldType = fieldType;
        this.row = row;
        this.column = column;
    }

    public FieldType getFieldType() {
        return fieldType;
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
        return FieldType.END.equals(fieldType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultField that = (DefaultField) o;

        return row == that.row && column == that.column && fieldType == that.fieldType;
    }

    @Override
    public int hashCode() {
        int result = fieldType.hashCode();
        result = 31 * result + row;
        result = 31 * result + column;
        return result;
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldType=" + fieldType +
                ", row=" + row +
                ", column=" + column +
                '}';
    }
}
