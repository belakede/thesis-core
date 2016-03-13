package me.belakede.thesis.game.board;

@FunctionalInterface
public interface SecretPassage {

    boolean isPartOfSecretPassage(RoomField roomField);

}
