package me.belakede.thesis.game.board;

import me.belakede.thesis.game.equipment.Room;

import java.util.Optional;


public interface RoomField extends Field {

    Room getRoom();

    Optional<Room> getSecretRoom();

}
