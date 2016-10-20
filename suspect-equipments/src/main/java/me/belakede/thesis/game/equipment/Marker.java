package me.belakede.thesis.game.equipment;

public enum Marker {

    NONE(" "), MAYBE("!"), MAYBE_NOT("/"), YES("+"), NOT("-"), QUESTION("?");

    private final String value;

    Marker(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
