package me.belakede.thesis.internal.game;

import me.belakede.thesis.game.Game;
import me.belakede.thesis.game.Player;
import me.belakede.thesis.game.board.Board;
import me.belakede.thesis.game.board.RoomField;
import me.belakede.thesis.game.equipment.*;
import me.belakede.thesis.game.field.Field;
import me.belakede.thesis.internal.game.equipment.DefaultPairOfDice;
import me.belakede.thesis.internal.game.util.Cases;

import java.util.*;

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
    public Case getMystery() {
        return mystery;
    }

    @Override
    public Map<Figurine, Field> getPositions() {
        return Collections.unmodifiableMap(new HashMap<>(positions));
    }

    @Override
    public Player getCurrentPlayer() {
        return players.getCurrent();
    }

    @Override
    public Player getNextPlayer() {
        return players.getNext();
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
        changePlayerStatusIfMadeGroundlessAccusation();
    }

    @Override
    public void next() {
        players.next();
    }

    @Override
    public boolean isGameEnded() {
        return mystery.isSolved() || !players.hasNext();
    }

    private void changePositionsBySuspicion(Suspicion suspicion) {
        Suspect suspect = suspicion.getSuspect();
        Weapon weapon = suspicion.getWeapon();
        Optional<RoomField> roomField = findRoomFieldByRoom(suspicion.getRoom());
        if (roomField.isPresent()) {
            Set<Field> fields = new HashSet<>(roomField.get().getFields());
            Set<Field> innerFields = new HashSet<>(fields);
            innerFields.removeAll(roomField.get().getExitFields());
            changeFigurinePositionIfNecessary(suspect, fields, positions.get(suspect), changePosition(innerFields));
            changeFigurinePositionIfNecessary(weapon, fields, positions.get(weapon), changePosition(innerFields));
        }
    }

    private Field changePosition(Collection<Field> fields) {
        Optional<Field> firstEmptyField = fields.stream().filter(f -> !positions.containsValue(f)).findFirst();
        if (!firstEmptyField.isPresent()) {
            firstEmptyField = fields.stream().findAny();
        }
        return firstEmptyField.get();
    }

    private void changeMysteryBySuspicion(Suspicion suspicion) {
        mystery = mystery.accuse(suspicion);
    }

    private void changePlayerStatusIfMadeGroundlessAccusation() {
        if (!mystery.isSolved()) {
            players.getCurrent().makeGroundlessAccusation();
        }
    }

    private Optional<RoomField> findRoomFieldByRoom(Room room) {
        return board.getRoomFields().stream()
                .filter(rf -> rf.getRoom().equals(room)).findFirst();
    }

    private void changeFigurinePositionIfNecessary(Figurine figurine, Set<Field> fields, Field o, Field value) {
        if (!fields.contains(o)) {
            positions.put(figurine, value);
        }
    }
}
