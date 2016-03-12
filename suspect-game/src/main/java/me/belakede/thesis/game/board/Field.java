package me.belakede.thesis.game.board;

import java.io.Serializable;

public interface Field extends Serializable {

    FieldType getFieldType();

    int getRow();

    int getColumn();

    boolean canMakeAnAccusation();

}
