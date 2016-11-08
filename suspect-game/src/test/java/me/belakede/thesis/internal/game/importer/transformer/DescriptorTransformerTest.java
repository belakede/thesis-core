package me.belakede.thesis.internal.game.importer.transformer;

import me.belakede.thesis.game.board.RoomField;
import me.belakede.thesis.game.board.SecretPassage;
import me.belakede.thesis.game.equipment.Room;
import me.belakede.thesis.game.field.Field;
import me.belakede.thesis.internal.game.board.DefaultRoomField;
import me.belakede.thesis.internal.game.board.DefaultSecretPassage;
import me.belakede.thesis.internal.game.field.FieldFactory;
import me.belakede.thesis.internal.game.importer.domain.RoomDescription;
import me.belakede.thesis.internal.game.importer.domain.RoomFieldDescriptor;
import me.belakede.thesis.internal.game.importer.domain.SecretPassageDescriptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class DescriptorTransformerTest {

    private DescriptorTransformer testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new DescriptorTransformer(getFields(), getRoomDescriptor());
    }

    @Test
    public void testGetRoomFieldsShouldReturnWithAFourLengthSet() throws Exception {
        Set<RoomField> roomFields = testSubject.getRoomFields();
        Assert.assertEquals(4, roomFields.size());
    }

    @Test
    public void testGetRoomFieldsShouldReturnASetWhichContainsABathroom() {
        Field[][] fields = getFields();
        RoomField bathroom = new DefaultRoomField(Room.BATHROOM,
                Arrays.asList(fields[0][8], fields[0][7], fields[0][6], fields[1][7]),
                Arrays.asList(fields[1][6], fields[1][8]));
        Set<RoomField> roomFields = testSubject.getRoomFields();
        Assert.assertTrue(roomFields.contains(bathroom));
    }

    @Test
    public void testGetRoomFieldsShouldReturnASetWhichContainsABathroomExactWithTheTwoSameExitFields() {
        Field[][] fields = getFields();
        Set<Field> exitFields = new HashSet<>(Arrays.asList(fields[1][6], fields[1][8]));
        RoomField bathroom = new DefaultRoomField(Room.BATHROOM,
                Arrays.asList(fields[0][8], fields[0][7], fields[0][6], fields[1][7]),
                exitFields);
        Set<RoomField> roomFields = testSubject.getRoomFields();
        Assert.assertTrue(roomFields.contains(bathroom));
        Assert.assertEquals(exitFields, roomFields.stream().filter(f -> Room.BATHROOM.equals(f.getRoom())).findFirst().get().getExitFields());
    }

    @Test
    public void testGetRoomFieldsShouldReturnASetWhichContainsABathroomExactWithTheSameFields() {
        Field[][] fields = getFields();
        Set<Field> bathroomFields = new HashSet<>(Arrays.asList(fields[0][8], fields[0][7], fields[0][6], fields[1][7]));
        Set<Field> bathroomExitFields = new HashSet<>(Arrays.asList(fields[1][6], fields[1][8]));
        RoomField bathroom = new DefaultRoomField(Room.BATHROOM,
                bathroomFields,
                bathroomExitFields);
        Set<RoomField> roomFields = testSubject.getRoomFields();
        Set<Field> expected = new HashSet<>(bathroomExitFields);
        expected.addAll(bathroomFields);
        Assert.assertTrue(roomFields.contains(bathroom));
        Assert.assertEquals(expected, roomFields.stream().filter(f -> Room.BATHROOM.equals(f.getRoom())).findFirst().get().getFields());
    }

    @Test
    public void testGetSecretPassagesShouldReturnWithTwoLengthSet() throws Exception {
        Set<SecretPassage> secretPassages = testSubject.getSecretPassages();
        Assert.assertEquals(2, secretPassages.size());
    }

    @Test
    public void testGetSecretPassagesShouldReturnASetWhichContainsASecretPassageBetweenTheBedroomAndTheKitchen() {
        Field[][] fields = getFields();
        RoomField bedroom = getBedroom(fields);
        RoomField kitchen = getKitchen(fields);
        SecretPassage secretPassage = new DefaultSecretPassage(bedroom, kitchen);
        Set<SecretPassage> secretPassages = testSubject.getSecretPassages();
        Assert.assertTrue(secretPassages.contains(secretPassage));
    }

    private RoomField getBedroom(Field[][] fields) {
        Set<Field> bedRoomFields = new HashSet<>(Arrays.asList(fields[0][0], fields[0][1], fields[0][2], fields[1][0], fields[1][2]));
        Set<Field> bedRoomExitFields = new HashSet<>(Arrays.asList(fields[1][1]));
        return new DefaultRoomField(Room.BEDROOM,
                bedRoomFields,
                bedRoomExitFields);
    }

    private RoomField getKitchen(Field[][] fields) {
        Set<Field> kitchenFields = new HashSet<>(Arrays.asList(fields[8][8], fields[8][7], fields[8][6], fields[7][8], fields[7][6]));
        Set<Field> kitchenExitFields = new HashSet<>(Arrays.asList(fields[7][7]));
        return new DefaultRoomField(Room.KITCHEN,
                kitchenFields,
                kitchenExitFields);
    }

    /**
     * Generate the following board
     * <p>
     * 012345678
     * 0 RRRWWWRRR
     * 1 RRRFFFRRR
     * 2 WFFFFFFFW
     * 3 SFFFFFFFS
     * 4 WFFFEFFFW
     * 5 SFFFFFFFS
     * 6 WFFFFFFFW
     * 7 RRRWWWRRR
     * 8 RRRFFFRRR
     *
     * @return
     */
    private Field[][] getFields() {
        Field[][] fields = new Field[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 0 || j == 0 || i == 8 || j == 8) {
                    fields[i][j] = FieldFactory.getFieldBySymbol(i, j, 'W');
                } else if (i == 4 && j == 4) {
                    fields[i][j] = FieldFactory.getFieldBySymbol(i, j, 'E');
                } else if ((i == 3 && (j == 0 || j == 8)) || (i == 5 && (j == 0 || j == 8))) {
                    fields[i][j] = FieldFactory.getFieldBySymbol(i, j, 'S');
                } else {
                    fields[i][j] = FieldFactory.getFieldBySymbol(i, j, 'F');
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                fields[i][j] = FieldFactory.getFieldBySymbol(i, j, 'R');
            }
        }
        for (int i = 7; i < 9; i++) {
            for (int j = 6; j < 9; j++) {
                fields[i][j] = FieldFactory.getFieldBySymbol(i, j, 'R');
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 6; j < 9; j++) {
                fields[i][j] = FieldFactory.getFieldBySymbol(i, j, 'R');
            }
        }
        for (int i = 7; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                fields[i][j] = FieldFactory.getFieldBySymbol(i, j, 'R');
            }
        }
        return fields;
    }

    private RoomDescription getRoomDescriptor() {
        RoomDescription roomDescription = new RoomDescription();
        roomDescription.setRoomFields(getRoomFieldList());
        roomDescription.setSecretPassages(getSecretPassageList());
        return roomDescription;
    }

    private List<RoomFieldDescriptor> getRoomFieldList() {
        List<RoomFieldDescriptor> roomFieldDescriptors = new ArrayList<>();
        roomFieldDescriptors.add(generateRoomField(Room.BEDROOM, "0,0;0,1;0,2;1,0;1,2", "1,1"));
        roomFieldDescriptors.add(generateRoomField(Room.KITCHEN, "8,8;8,7;8,6;7,8;7,6", "7,7"));
        roomFieldDescriptors.add(generateRoomField(Room.BATHROOM, "0,8;0,7;0,6;1,7", "1,6;1,8"));
        roomFieldDescriptors.add(generateRoomField(Room.LIBRARY, "8,0;8,1;8,2;7,1", "7,0;7,2"));
        return roomFieldDescriptors;
    }

    private List<SecretPassageDescriptor> getSecretPassageList() {
        List<SecretPassageDescriptor> secretPassageDescriptors = new ArrayList<>();
        secretPassageDescriptors.add(new SecretPassageDescriptor(Room.BEDROOM, Room.KITCHEN));
        secretPassageDescriptors.add(new SecretPassageDescriptor(Room.BATHROOM, Room.LIBRARY));
        return secretPassageDescriptors;
    }

    /**
     * Use the following format for fields and exitFields:
     * 1,2;1,3;1,4;
     *
     * @param fields     Well formatted list of coords
     * @param exitFields Well formatted list of coords
     * @return
     */
    private RoomFieldDescriptor generateRoomField(Room room, String fields, String exitFields) {
        List<List<Integer>> fieldList = getListOfCoordListFromString(fields);
        List<List<Integer>> exitFieldList = getListOfCoordListFromString(exitFields);
        RoomFieldDescriptor roomFieldDescriptor = new RoomFieldDescriptor();
        roomFieldDescriptor.setRoom(room);
        roomFieldDescriptor.setFields(fieldList);
        roomFieldDescriptor.setExitFields(exitFieldList);
        return roomFieldDescriptor;
    }

    /**
     * Use the following format for fields:
     * 1,2;1,3;1,4;
     *
     * @param fields Well formatted list of coords
     * @return
     */
    private List<List<Integer>> getListOfCoordListFromString(String fields) {
        return Arrays.stream(fields.split(";")).map(this::coordsAsStringToList).collect(Collectors.toList());
    }

    /**
     * Use the following format for coord pair:
     * 1,2
     *
     * @param coords Well formatted list of a coord pair
     * @return
     */
    private List<Integer> coordsAsStringToList(String coords) {
        return Arrays.stream(coords.split(",")).map(Integer::valueOf).collect(Collectors.toList());
    }

}