package me.belakede.thesis.game.board;

public enum BoardType {

    DEFAULT("default", 27, 27),

    ADVANCED("advanced", 27, 27);

    private final String filename;
    private final int rows;
    private final int columns;

    BoardType(String filename, int rows, int columns) {
        this.filename = filename;
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        return filename;
    }

}
