package me.belakede.thesis.game.board;

public enum BoardType {

    DEFAULT("default", 27, 27),

    ADVANCED("advanced", 27, 27);

    private final String filename;
    private final int N;
    private final int M;

    BoardType(String filename, int rows, int columns) {
        this.filename = filename;
        this.N = rows;
        this.M = columns;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    @Override
    public String toString() {
        return filename;
    }

}
