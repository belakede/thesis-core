package me.belakede.thesis.internal.game;

import me.belakede.thesis.game.Game;
import me.belakede.thesis.game.board.Board;
import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.board.RoomField;
import me.belakede.thesis.game.equipment.*;
import me.belakede.thesis.internal.game.equipment.DefaultPairOfDice;
import me.belakede.thesis.internal.game.util.Cases;

import java.util.HashMap;
import java.util.Map;

public final class DefaultGame implements Game {

    private final Board board;
    private final PlayerCycle players;
    private final Map<Figurine, Field> positions;
    private Case mystery;
    private PairOfDice pairOfDice;

    public DefaultGame(Board board, PlayerCycle players, Map<Figurine, Field> positions) {
        this(board, Cases.generate(), players, positions);
    }

    public DefaultGame(Board board, Case mystery, PlayerCycle players, Map<Figurine, Field> positions) {
        this.board = board;
        this.mystery = mystery;
        this.players = players;
        this.pairOfDice = DefaultPairOfDice.create();
        this.positions = new HashMap<>(positions);
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Map<Figurine, Field> getPositions() {
        return new HashMap<>(positions);
    }

    @Override
    public PairOfDice roll() {
        pairOfDice = pairOfDice.roll();
        return pairOfDice;
    }

    @Override
    public void move(Field field) {
        Suspect figurine = players.getCurrent().getFigurine();
        Field position = positions.get(figurine);
        if (board.isAvailable(position, field)) {
            positions.put(figurine, field);
        }
    }

    @Override
    public void suspect(Suspicion suspicion) {
        changePositionsBySuspicion(suspicion);
    }

    @Override
    public void accuse(Suspicion suspicion) {
        changePositionsBySuspicion(suspicion);
        changeMysteryBySuspicion(suspicion);
    }

    @Override
    public boolean isGameEnded() {
        return mystery.isSolved() || !players.hasNext();
    }

    private void changePositionsBySuspicion(Suspicion suspicion) {
        Suspect suspect = suspicion.getSuspect();
        Weapon weapon = suspicion.getWeapon();
        Field field = findRoomFieldByRoom(suspicion.getRoom());
        positions.put(suspect, field);
        positions.put(weapon, field);
    }

    private void changeMysteryBySuspicion(Suspicion suspicion) {
        mystery = mystery.accuse(suspicion);
    }

    private RoomField findRoomFieldByRoom(Room room) {
        return board.getRoomFields()
                .stream()
                .map(field -> (RoomField) field)
                .filter(roomField -> room.equals(roomField.getRoom()))
                .findFirst()
                .get();
    }
}