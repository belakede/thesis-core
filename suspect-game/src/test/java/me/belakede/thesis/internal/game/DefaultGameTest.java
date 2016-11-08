package me.belakede.thesis.internal.game;

import me.belakede.thesis.game.Game;
import me.belakede.thesis.game.Player;
import me.belakede.thesis.game.board.Board;
import me.belakede.thesis.game.board.RoomField;
import me.belakede.thesis.game.board.SecretPassage;
import me.belakede.thesis.game.equipment.*;
import me.belakede.thesis.game.field.Field;
import me.belakede.thesis.internal.game.board.DefaultBoard;
import me.belakede.thesis.internal.game.board.DefaultRoomField;
import me.belakede.thesis.internal.game.board.DefaultSecretPassage;
import me.belakede.thesis.internal.game.equipment.DefaultCase;
import me.belakede.thesis.internal.game.equipment.DefaultSuspicion;
import me.belakede.thesis.internal.game.field.FieldFactory;
import me.belakede.thesis.internal.game.util.Players;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.Assert.*;


public class DefaultGameTest {

    private GameTestData testData;
    private Game testSubject;

    @Before
    public void setUp() throws Exception {
        testData = getComplexGameInstallation();
        Case mystery = new DefaultCase(new DefaultSuspicion(Suspect.SCARLET, Room.DINING_ROOM, Weapon.ROPE));
        testSubject = new DefaultGame(testData.getBoard(), mystery, testData.getPlayerCycle(), testData.getPositions());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetPositionsShouldThrowUnsupportedOperationExceptionWhenTryToPutNewValue() throws Exception {
        testSubject.getPositions().put(Suspect.GREEN, Mockito.mock(Field.class));
    }

    @Test
    public void testRollShouldChangePairOfDiceAfterEveryCall() throws Exception {
        PairOfDice firstRoll = testSubject.roll();
        PairOfDice secondRoll = testSubject.roll();
        assertNotSame(firstRoll, secondRoll);
    }

    @Test
    public void testMoveShouldChangeTheCurrentPlayersFigurinePositionToTheSpecifiedField() throws Exception {
        // GIVEN
        Player currentPlayer = testSubject.getCurrentPlayer();
        Field[][] fields = testData.getFields();
        Field nextField = fields[0][4];
        // WHEN
        Map<Figurine, Field> previous = testSubject.getPositions();
        Mockito.when(currentPlayer.getFigurine()).thenReturn(Suspect.GREEN);
        testSubject.move(nextField);
        // THEN
        Mockito.verify(currentPlayer).getFigurine();
        Map<Figurine, Field> actual = testSubject.getPositions();
        assertNotEquals(previous, actual);
    }

    @Test
    public void testSuspectShouldChangeTheSuspectAndTheWeaponFigurinesPositionToTheSpecifiedRoom() throws Exception {
        // GIVEN
        Suspicion suspicion = new DefaultSuspicion(Suspect.GREEN, Room.BILLIARD_ROOM, Weapon.CANDLESTICK);
        Optional<RoomField> optionalBilliardRoom = testData.getBoard().getRoomFields().stream().filter(rf -> Room.BILLIARD_ROOM.equals(rf.getRoom())).findFirst();
        if (!optionalBilliardRoom.isPresent()) {
            fail("There is no billiard room");
        }
        RoomField billiardRoom = optionalBilliardRoom.get();
        // WHEN
        Map<Figurine, Field> previous = testSubject.getPositions();
        testSubject.suspect(suspicion);
        // THEN
        Map<Figurine, Field> actual = testSubject.getPositions();
        assertNotEquals(previous, actual);
        assertTrue(billiardRoom.isPartOfRoom(actual.get(Suspect.GREEN)));
        assertTrue(billiardRoom.isPartOfRoom(actual.get(Weapon.CANDLESTICK)));
    }

    @Test
    public void testAccuseShouldChangeTheSuspectAndTheWeaponFigurinesPositionToTheSpecifiedRoom() throws Exception {
        // GIVEN
        Suspicion suspicion = new DefaultSuspicion(Suspect.GREEN, Room.BILLIARD_ROOM, Weapon.KNIFE);
        Optional<RoomField> optionalBilliardRoom = testData.getBoard().getRoomFields().stream().filter(rf -> Room.BILLIARD_ROOM.equals(rf.getRoom())).findFirst();
        if (!optionalBilliardRoom.isPresent()) {
            fail("There is no billiard room");
        }
        RoomField billiardRoom = optionalBilliardRoom.get();
        // WHEN
        Map<Figurine, Field> previous = testSubject.getPositions();
        testSubject.accuse(suspicion);
        // THEN
        Map<Figurine, Field> actual = testSubject.getPositions();
        assertNotEquals(previous, actual);
        assertTrue(billiardRoom.isPartOfRoom(actual.get(Suspect.GREEN)));
        assertTrue(billiardRoom.isPartOfRoom(actual.get(Weapon.KNIFE)));
    }

    @Test
    public void testIsGameEndedShouldReturnFalseAfterAccuseIsInCorrect() throws Exception {
        // GIVEN
        Suspicion suspicion = new DefaultSuspicion(Suspect.GREEN, Room.DINING_ROOM, Weapon.ROPE);
        // WHEN
        testSubject.accuse(suspicion);
        // THEN
        assertFalse(testSubject.isGameEnded());
    }

    @Test
    public void testIsGameEndedShouldReturnTrueAfterAccuseIsCorrect() throws Exception {
        // GIVEN
        Suspicion suspicion = new DefaultSuspicion(Suspect.SCARLET, Room.DINING_ROOM, Weapon.ROPE);
        // WHEN
        testSubject.accuse(suspicion);
        // THEN
        assertTrue(testSubject.isGameEnded());
    }

    @Test
    public void testNextShouldChangeCurrentPlayerToTheNextPlayer() throws Exception {
        // GIVEN
        Player expected = testSubject.getNextPlayer();
        // WHEN
        testSubject.next();
        Player actual = testSubject.getCurrentPlayer();
        // THEN
        assertEquals(expected, actual);
    }

    @Test
    public void testIsGameEndedShouldReturnTrueAfterAlmostAllPlayerMadeWrongAccusation() throws Exception {
        // GIVEN
        Suspicion suspicion = new DefaultSuspicion(Suspect.SCARLET, Room.DINING_ROOM, Weapon.ROPE);
        // WHEN
        testSubject.accuse(suspicion);
        testSubject.next();
        testSubject.accuse(suspicion);
        testSubject.next();
        testSubject.accuse(suspicion);
        // THEN
        assertTrue(testSubject.isGameEnded());
    }

    /**
     * Return with the following board
     * <p>
     * 0  1  2  3  4  5  6  7  8
     * 0 [ ][w][R][W][ ][W][R][R][R]
     * 1 [ ][D][R][W][ ][D][R][R][R]
     * 2 [ ][W][W][W][ ][W][R][R][R]
     * 3 [ ][ ][ ][ ][ ][W][W][R][W]
     * 4 [ ][ ][ ][ ][ ][ ][ ][ ][ ]
     * 5 [W][D][W][W][ ][ ][ ][ ][ ]
     * 6 [R][R][R][W][ ][ ][W][W][D]
     * 7 [R][R][R][D][ ][ ][W][R][R]
     * 8 [R][R][R][W][ ][ ][W][R][R]
     * <p>
     * There is a secret passage from top left corner to bottom right corner.
     * Player figurines are the following position:
     * GREEN:    [0][0]
     * MUSTARD:    [8][5]
     * PLUM:    [1][1]
     * WHITE:    [4][6]
     *
     * @return BoardTestData
     */
    private GameTestData getComplexGameInstallation() {
        int size = 9;
        Field[][] fields = new Field[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fields[i][j] = FieldFactory.getFieldBySymbol(i, j, 'F');
            }
        }
        List<Field> firstRoomFields = new ArrayList<>();
        List<Field> secondRoomFields = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 6; j < size; j++) {
                fields[i][j] = FieldFactory.getFieldBySymbol(i, j, 'R');
                fields[j][i] = FieldFactory.getFieldBySymbol(i, j, 'R');
                firstRoomFields.add(fields[j][i]);
                secondRoomFields.add(fields[i][j]);
            }
        }
        for (int i = 0; i < 4; i++) {
            fields[i][5] = FieldFactory.getFieldBySymbol(i, 5, 'W');
            fields[5][i] = FieldFactory.getFieldBySymbol(5, i, 'W');
        }
        for (int j = 6; j < size; j++) {
            fields[j][3] = FieldFactory.getFieldBySymbol(j, 3, 'W');
            fields[3][j] = FieldFactory.getFieldBySymbol(3, j, 'W');
        }
        List<Field> firstExitFields = new ArrayList<>();
        List<Field> secondExitFields = new ArrayList<>();
        fields[5][1] = FieldFactory.getFieldBySymbol(5, 1, 'R');
        fields[7][3] = FieldFactory.getFieldBySymbol(7, 3, 'R');
        fields[1][5] = FieldFactory.getFieldBySymbol(1, 5, 'R');
        fields[3][7] = FieldFactory.getFieldBySymbol(3, 7, 'R');
        firstExitFields.add(fields[5][1]);
        firstExitFields.add(fields[7][3]);
        secondExitFields.add(fields[1][5]);
        secondExitFields.add(fields[3][7]);

        List<Field> thirdRoomFields = new ArrayList<>();
        for (int i = 7; i < size; i++) {
            for (int j = 7; j < size; j++) {
                fields[i][j] = FieldFactory.getFieldBySymbol(i, j, 'R');
                thirdRoomFields.add(fields[i][j]);
            }
        }
        List<Field> thirdExitFields = new ArrayList<>();
        fields[6][8] = FieldFactory.getFieldBySymbol(6, 8, 'R');
        thirdExitFields.add(fields[6][8]);
        for (int i = 6; i < size; i++) {
            fields[i][6] = FieldFactory.getFieldBySymbol(i, 6, 'W');
        }
        fields[6][7] = FieldFactory.getFieldBySymbol(6, 7, 'W');

        List<Field> fourthRoomFields = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j < 4; j++) {
                fields[i][j] = FieldFactory.getFieldBySymbol(i, j, 'W');
            }
        }
        for (int i = 0; i < 2; i++) {
            fields[i][2] = FieldFactory.getFieldBySymbol(i, 2, 'R');
            fourthRoomFields.add(fields[i][2]);
        }
        List<Field> fourthExitFields = new ArrayList<>();
        fields[1][1] = FieldFactory.getFieldBySymbol(1, 1, 'R');
        fourthExitFields.add(fields[1][1]);

        RoomField firstRoom = new DefaultRoomField(Room.BILLIARD_ROOM, firstRoomFields, firstExitFields);
        RoomField secondRoom = new DefaultRoomField(Room.BEDROOM, secondRoomFields, secondExitFields);
        RoomField thirdRoom = new DefaultRoomField(Room.KITCHEN, thirdRoomFields, thirdExitFields);
        RoomField fourthRoom = new DefaultRoomField(Room.BATHROOM, fourthRoomFields, fourthExitFields);

        SecretPassage secretPassage = new DefaultSecretPassage(thirdRoom, fourthRoom);

        Set<RoomField> roomFields = new HashSet<>();
        roomFields.add(firstRoom);
        roomFields.add(secondRoom);
        roomFields.add(thirdRoom);
        roomFields.add(fourthRoom);

        Set<SecretPassage> secretPassages = new HashSet<>();
        secretPassages.add(secretPassage);

        Board board = new DefaultBoard(BoardType.DEFAULT, fields, roomFields, secretPassages);

        Player first = Mockito.mock(Player.class);
        Player second = Mockito.mock(Player.class);
        Player third = Mockito.mock(Player.class);
        Player fourth = Mockito.mock(Player.class);
        PlayerCycle cycle = Players.createPlayerCycle(first, second, third, fourth);

        Map<Figurine, Field> positions = new HashMap<>();
        positions.put(Suspect.GREEN, fields[0][0]);
        positions.put(Suspect.MUSTARD, fields[8][5]);
        positions.put(Suspect.PLUM, fields[1][1]);
        positions.put(Suspect.WHITE, fields[4][6]);
        positions.put(Weapon.CANDLESTICK, fields[0][7]);
        positions.put(Weapon.KNIFE, fields[8][7]);
        return new GameTestData(board, fields, cycle, positions);
    }

    private static final class GameTestData {
        private final Board board;
        private final Field[][] fields;
        private final PlayerCycle playerCycle;
        private final Map<Figurine, Field> positions;

        GameTestData(Board board, Field[][] fields, PlayerCycle playerCycle, Map<Figurine, Field> positions) {
            this.board = board;
            this.fields = fields;
            this.playerCycle = playerCycle;
            this.positions = positions;
        }

        public Board getBoard() {
            return board;
        }

        Field[][] getFields() {
            return fields;
        }

        PlayerCycle getPlayerCycle() {
            return playerCycle;
        }

        Map<Figurine, Field> getPositions() {
            return positions;
        }
    }

}