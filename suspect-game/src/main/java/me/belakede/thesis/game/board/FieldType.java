package me.belakede.thesis.game.board;

public enum FieldType {

    WALL('W'),

    ROOM('R'),

    SIMPLE('F'),

    START('S'),

    END('E'),

    NULL('.');

    private final char symbol;

    FieldType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

}
