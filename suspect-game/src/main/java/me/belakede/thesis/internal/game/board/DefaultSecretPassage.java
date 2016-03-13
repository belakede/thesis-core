package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.board.RoomField;
import me.belakede.thesis.game.board.SecretPassage;

public final class DefaultSecretPassage implements SecretPassage {

    private final RoomField fromField;
    private final RoomField toField;

    public DefaultSecretPassage(RoomField fromField, RoomField toField) {
        this.fromField = fromField;
        this.toField = toField;
    }

    @Override
    public boolean isPartOfSecretPassage(RoomField roomField) {
        return fromField.equals(roomField) || toField.equals(roomField);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DefaultSecretPassage that = (DefaultSecretPassage) o;

        return fromField.equals(that.fromField) && toField.equals(that.toField);
    }

    @Override
    public int hashCode() {
        int result = fromField.hashCode();
        result = 31 * result + toField.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SecretPassage{" +
                "fromField=" + fromField +
                ", toField=" + toField +
                '}';
    }
}
