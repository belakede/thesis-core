package me.belakede.thesis.game.board;

public interface Field {

    FieldType getFieldType();

    boolean canMakeAnAccusation();

    int getColumn();

    int getRow();

}
