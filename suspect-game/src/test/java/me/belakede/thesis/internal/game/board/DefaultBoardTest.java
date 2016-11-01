package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.board.*;
import me.belakede.thesis.game.equipment.BoardType;
import me.belakede.thesis.game.equipment.Room;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class DefaultBoardTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testGetStartingFieldsShouldThrowUnsupportedOperationExceptionWhenTryToInsertANewValue() throws Exception {
        // GIVEN
        Field extraField = Mockito.mock(Field.class);
        FieldMatrix fieldMatrix = Mockito.mock(FieldMatrix.class);
        DefaultBoard testSubject = getSimpleBoardInstallation(fieldMatrix);
        // WHEN
        Mockito.when(fieldMatrix.getStartingFields()).thenReturn(new HashSet<>());
        Set<Field> startingFields = testSubject.getStartingFields();
        // THEN
        startingFields.add(extraField);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetRoomFieldsShouldThrowUnsupportedOperationExceptionWhenTryToInsertANewValue() throws Exception {
        // GIVEN
        RoomField extraField = Mockito.mock(RoomField.class);
        FieldMatrix fieldMatrix = Mockito.mock(FieldMatrix.class);
        DefaultBoard testSubject = getSimpleBoardInstallation(fieldMatrix);
        // WHEN
        Mockito.when(fieldMatrix.getStartingFields()).thenReturn(new HashSet<>());
        Set<RoomField> roomFields = testSubject.getRoomFields();
        // THEN
        roomFields.add(extraField);
    }

    @Test
    public void testGetRoomFieldsShouldReturnWithAThreeElementSetOnTheComplexBoard() throws Exception {
        // GIVEN
        BoardTestData testData = getComplexBoardInstallation();
        Board testSubject = testData.getBoard();
        // WHEN
        Set<RoomField> actual = testSubject.getRoomFields();
        // THEN
        Set<RoomField> expected = testData.getRoomFields();
        assertEquals(expected, actual);
    }

    @Test
    public void testIsAvailableShouldReturnTrueWhenFromAndToAreSimpleFieldsAndDistanceIsFour() throws Exception {
        // GIVEN
        BoardTestData testData = getComplexBoardInstallation();
        Board testSubject = testData.getBoard();
        Field[][] fields = testData.getFields();
        Field from = fields[4][2];
        Field to = fields[4][6];
        // WHEN
        // THEN
        assertTrue(testSubject.isAvailable(from, to));
    }

    @Test
    public void testIsAvailableShouldReturnTrueWhenFromAndToAreSimpleFieldsAndDistanceIsTwelve() throws Exception {
        // GIVEN
        BoardTestData testData = getComplexBoardInstallation();
        Board testSubject = testData.getBoard();
        Field[][] fields = testData.getFields();
        Field from = fields[7][5];
        Field to = fields[0][0];
        // WHEN
        // THEN
        assertTrue(testSubject.isAvailable(from, to));
    }

    @Test
    public void testIsAvailableShouldReturnFalseWhenFromAndToAreSimpleFieldsAndDistanceIsThirteen() throws Exception {
        // GIVEN
        BoardTestData testData = getComplexBoardInstallation();
        Board testSubject = testData.getBoard();
        Field[][] fields = testData.getFields();
        Field from = fields[0][0];
        Field to = fields[8][5];
        // WHEN
        // THEN
        assertFalse(testSubject.isAvailable(from, to));
    }


    @Test
    public void testIsAvailableShouldReturnTrueWhenFromAndToAreRoomFieldsAndDistanceIsNineteenButHasSecretPassageBetweenThem() throws Exception {
        // GIVEN
        BoardTestData testData = getComplexBoardInstallation();
        Board testSubject = testData.getBoard();
        Field[][] fields = testData.getFields();
        Field from = fields[8][8];
        Field to = fields[0][2];
        // WHEN
        // THEN
        assertTrue(testSubject.isAvailable(from, to));
    }

    @Test
    public void testIsAvailableShouldReturnTrueWhenFromAndToAreRoomFieldsAndDistanceIsMoreThenThirteenButDoorsDistanceOnlyEight() throws Exception {
        // GIVEN
        BoardTestData testData = getComplexBoardInstallation();
        Board testSubject = testData.getBoard();
        Field[][] fields = testData.getFields();
        Field from = fields[8][0];
        Field to = fields[0][8];
        // WHEN
        // THEN
        assertTrue(testSubject.isAvailable(from, to));
    }

    @Test
    public void testAvailableFieldsShouldReturnOneElementSetWhenFromIsASimpleFieldAndStepIsZero() throws Exception {
        // GIVEN
        BoardTestData testData = getComplexBoardInstallation();
        Board testSubject = testData.getBoard();
        Field[][] fields = testData.getFields();
        Field from = fields[4][2];
        // WHEN
        Set<Field> actual = testSubject.availableFields(from, 0);
        // THEN
        Set<Field> expected = new HashSet<>(Arrays.asList(from));
        assertEquals(expected, actual);
    }

    @Test
    public void testAvailableFieldsShouldReturnFourElementSetWhenFromIsASimpleFieldAndStepIsOne() throws Exception {
        // GIVEN
        BoardTestData testData = getComplexBoardInstallation();
        Board testSubject = testData.getBoard();
        Field[][] fields = testData.getFields();
        Field from = fields[4][2];
        // WHEN
        Set<Field> actual = testSubject.availableFields(from, 1);
        // THEN
        Set<Field> expected = new HashSet<>(Arrays.asList(from, fields[3][2], fields[4][3], fields[4][1]));
        assertEquals(expected, actual);
    }

    @Test
    public void testAvailableFieldsShouldReturnTheSetOfFieldsInRoomWhenFromIsARoomFieldStepIsZeroAndThereIsNoSecretPassageToAnotherRoom() throws Exception {
        // GIVEN
        BoardTestData testData = getComplexBoardInstallation();
        Board testSubject = testData.getBoard();
        Field[][] fields = testData.getFields();
        Set<RoomField> roomFields = testData.getRoomFields();
        Field from = fields[5][1];
        Optional<RoomField> roomField = roomFields.stream().filter(rf -> rf.isPartOfRoom(from)).findAny();
        if (!roomField.isPresent()) {
            fail("From field is not part of any room.");
        }
        // WHEN
        Set<Field> actual = testSubject.availableFields(from, 0);
        // THEN
        Set<Field> expected = roomField.get().getFields();
        assertEquals(expected, actual);
    }

    @Test
    public void testAvailableFieldsShouldReturnTheSetOfFieldsInTwoRoomWhenFromIsARoomFieldStepIsZeroAndThereIsASecretPassageToAnotherRoom() throws Exception {
        // GIVEN
        BoardTestData testData = getComplexBoardInstallation();
        Board testSubject = testData.getBoard();
        Field[][] fields = testData.getFields();
        Set<RoomField> roomFields = testData.getRoomFields();
        Set<SecretPassage> secretPassages = testData.getSecretPassages();
        Field from = fields[1][2];

        Optional<RoomField> roomField = roomFields.stream().filter(rf -> rf.isPartOfRoom(from)).findAny();
        if (!roomField.isPresent()) {
            fail("From field is not part of any room.");
        }
        if (secretPassages.size() < 1) {
            fail("Missing secret passage");
        }
        SecretPassage secretPassage = secretPassages.stream().findFirst().get();
        Set<RoomField> secretRoomFields = roomFields.stream().filter(secretPassage::isPartOfSecretPassage).collect(Collectors.toSet());
        secretRoomFields.remove(roomField.get());
        if (secretRoomFields.size() < 1) {
            fail("Missing secret room");
        }
        RoomField secretRoom = secretRoomFields.stream().findAny().get();

        // WHEN
        Set<Field> actual = testSubject.availableFields(from, 0);
        // THEN
        Set<Field> expected = new HashSet<>(roomField.get().getFields());
        expected.addAll(secretRoom.getFields());

        assertEquals(expected, actual);
    }


    private DefaultBoard getSimpleBoardInstallation(FieldMatrix fieldMatrix) {
        RoomField firstRoomField = Mockito.mock(RoomField.class);
        RoomField secondRoomField = Mockito.mock(RoomField.class);
        SecretPassage secretPassage = Mockito.mock(SecretPassage.class);
        Set<RoomField> roomFields = new HashSet<>();
        Set<SecretPassage> secretPassages = new HashSet<>();
        roomFields.add(firstRoomField);
        roomFields.add(secondRoomField);
        secretPassages.add(secretPassage);
        return new DefaultBoard(BoardType.DEFAULT, fieldMatrix, roomFields, secretPassages);
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
     *
     * @return BoardTestData
     */
    private BoardTestData getComplexBoardInstallation() {
        int size = 9;
        Field[][] fields = new Field[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fields[i][j] = new DefaultField(FieldType.SIMPLE, i, j);
            }
        }
        List<Field> firstRoomFields = new ArrayList<>();
        List<Field> secondRoomFields = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 6; j < size; j++) {
                fields[i][j] = new DefaultField(FieldType.ROOM, i, j);
                fields[j][i] = new DefaultField(FieldType.ROOM, i, j);
                firstRoomFields.add(fields[j][i]);
                secondRoomFields.add(fields[i][j]);
            }
        }
        for (int i = 0; i < 4; i++) {
            fields[i][5] = new DefaultField(FieldType.WALL, i, 5);
            fields[5][i] = new DefaultField(FieldType.WALL, 5, i);
        }
        for (int j = 6; j < size; j++) {
            fields[j][3] = new DefaultField(FieldType.WALL, 3, j);
            fields[3][j] = new DefaultField(FieldType.WALL, j, 3);
        }
        List<Field> firstExitFields = new ArrayList<>();
        List<Field> secondExitFields = new ArrayList<>();
        fields[5][1] = new DefaultField(FieldType.ROOM, 5, 1);
        fields[7][3] = new DefaultField(FieldType.ROOM, 7, 3);
        fields[1][5] = new DefaultField(FieldType.ROOM, 1, 5);
        fields[3][7] = new DefaultField(FieldType.ROOM, 3, 7);
        firstExitFields.add(fields[5][1]);
        firstExitFields.add(fields[7][3]);
        secondExitFields.add(fields[1][5]);
        secondExitFields.add(fields[3][7]);

        List<Field> thirdRoomFields = new ArrayList<>();
        for (int i = 7; i < size; i++) {
            for (int j = 7; j < size; j++) {
                fields[i][j] = new DefaultField(FieldType.ROOM, i, j);
                thirdRoomFields.add(fields[i][j]);
            }
        }
        List<Field> thirdExitFields = new ArrayList<>();
        fields[6][8] = new DefaultField(FieldType.ROOM, 6, 8);
        thirdExitFields.add(fields[6][8]);
        for (int i = 6; i < size; i++) {
            fields[i][6] = new DefaultField(FieldType.WALL, i, 6);
        }
        fields[6][7] = new DefaultField(FieldType.WALL, 6, 7);

        List<Field> fourthRoomFields = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j < 4; j++) {
                fields[i][j] = new DefaultField(FieldType.WALL, i, j);
            }
        }
        for (int i = 0; i < 2; i++) {
            fields[i][2] = new DefaultField(FieldType.ROOM, i, 2);
            fourthRoomFields.add(fields[i][2]);
        }
        List<Field> fourthExitFields = new ArrayList<>();
        fields[1][1] = new DefaultField(FieldType.ROOM, 1, 1);
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

        return new BoardTestData(fields, roomFields, secretPassages);
    }

    private static final class BoardTestData {

        private final Field[][] fields;
        private final FieldMatrix fieldMatrix;
        private final Set<RoomField> roomFields;
        private final Set<SecretPassage> secretPassages;
        private final Board board;

        public BoardTestData(Field[][] fields, Set<RoomField> roomFields, Set<SecretPassage> secretPassages) {
            this.fields = fields;
            this.roomFields = roomFields;
            this.secretPassages = secretPassages;
            this.fieldMatrix = new FieldMatrix(fields.length, fields);
            this.board = new DefaultBoard(BoardType.DEFAULT, fieldMatrix, roomFields, secretPassages);
        }

        public Field[][] getFields() {
            return fields;
        }

        public FieldMatrix getFieldMatrix() {
            return fieldMatrix;
        }

        public Set<RoomField> getRoomFields() {
            return roomFields;
        }

        public Set<SecretPassage> getSecretPassages() {
            return secretPassages;
        }

        public Board getBoard() {
            return board;
        }
    }


}