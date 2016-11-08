package me.belakede.thesis.internal.game.field;

import me.belakede.thesis.game.field.Field;
import me.belakede.thesis.game.field.FieldType;
import me.belakede.thesis.internal.game.util.FieldTypes;

public final class FieldFactory {

    private static final Field NULL_FIELD;

    static {
        NULL_FIELD = new DefaultField(FieldType.NULL, -1, -1);
    }

    private FieldFactory() {
    }

    public static Field getFieldBySymbol(int row, int column, char symbol) {
        Field field = getNullField();
        FieldType fieldType = FieldTypes.findFieldTypeBySymbol(symbol);
        if (!FieldType.NULL.equals(fieldType)) {
            field = new DefaultField(fieldType, row, column);
        }
        return field;
    }

    public static Field getNullField() {
        return NULL_FIELD;
    }

}
