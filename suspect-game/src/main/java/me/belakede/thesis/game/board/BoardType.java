package me.belakede.thesis.game.board;

public enum BoardType {

    DEFAULT("default", 27),

    ADVANCED("advanced", 27);

    private final String filename;
    private final int size;

    BoardType(String filename, int size) {
        this.filename = filename;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return filename;
    }

}
