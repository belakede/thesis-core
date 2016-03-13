package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.board.FieldType;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class FieldTypes {

    private FieldTypes() {
    }

    public static FieldType findFieldTypeBySymbol(char symbol) {
        Stream<FieldType> stream = Arrays.stream(FieldType.values());
        Optional<FieldType> fieldType = stream.filter(ft -> ft.getSymbol() == symbol).findFirst();
        stream.close();
        return fieldType.orElse(FieldType.NULL);
    }

}
