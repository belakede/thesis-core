package me.belakede.thesis.game.field;

public interface Field {

    FieldType getFieldType();

    boolean canMakeAnAccusation();

    int getColumn();

    int getRow();

}
