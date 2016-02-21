package me.belakede.thesis.game.board;

import me.belakede.thesis.game.equipment.Room;

import java.util.Optional;
import java.util.Set;


public interface RoomField extends Field {

    Room getRoom();

    Optional<Room> getSecretRoom();

    Optional<Field> getSecretRoomField();

    Set<Field> getExitFields();

}
