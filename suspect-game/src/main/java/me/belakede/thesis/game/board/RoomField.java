package me.belakede.thesis.game.board;

import me.belakede.thesis.game.equipment.Room;

import java.util.Set;


public interface RoomField {

    Room getRoom();

    Set<Field> getFields();

    Set<Field> getExitFields();

    boolean isPartOfRoom(Field field);

}
