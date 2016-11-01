package me.belakede.thesis.internal.game.importer;

import me.belakede.thesis.game.board.Board;
import me.belakede.thesis.game.equipment.BoardType;
import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.board.RoomField;
import me.belakede.thesis.game.equipment.Room;
import me.belakede.thesis.internal.game.board.DefaultRoomField;
import me.belakede.thesis.internal.game.board.FieldFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MapLoaderTest {

    private MapLoader testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new MapLoader(BoardType.DEFAULT);
    }

    @Test
    public void testLoadDefaultShouldReturnABoardWithNineRoomsAndSixStartingFields() throws Exception {
        Board board = testSubject.load();

        assertEquals(9, board.getRoomFields().size());
        assertEquals(6, board.getStartingFields().size());
    }

    @Test
    public void testLoadDefaultShouldReturnABoardWhichContainsWorkroom() throws Exception {
        RoomField workroom = getWorkroom();
        Set<RoomField> actual = testSubject.load().getRoomFields();
        assertTrue(actual.contains(workroom));
    }

    @Test
    public void testLoadDefaultShouldReturnABoardWithTheSpecifiedWorkroom() throws Exception {
        RoomField workroom = getWorkroom();
        Set<RoomField> actual = testSubject.load().getRoomFields();
        assertTrue(actual.contains(workroom));
        assertEquals(workroom, actual.stream().filter(rf -> Room.WORKROOM.equals(rf.getRoom())).findFirst().get());
    }

    private RoomField getWorkroom() {
        return new DefaultRoomField(Room.WORKROOM, getWorkroomFields(), getWorkroomExitFields());
    }

    private Set<Field> getWorkroomFields() {
        return new HashSet<>(Arrays.asList(
                FieldFactory.getFieldBySymbol(21, 11, 'R'), FieldFactory.getFieldBySymbol(21, 12, 'R'),
                FieldFactory.getFieldBySymbol(21, 13, 'R'), FieldFactory.getFieldBySymbol(21, 14, 'R'),
                FieldFactory.getFieldBySymbol(21, 15, 'R'),
                FieldFactory.getFieldBySymbol(22, 11, 'R'), FieldFactory.getFieldBySymbol(22, 12, 'R'),
                FieldFactory.getFieldBySymbol(22, 13, 'R'), FieldFactory.getFieldBySymbol(22, 14, 'R'),
                FieldFactory.getFieldBySymbol(22, 15, 'R'),
                FieldFactory.getFieldBySymbol(23, 11, 'R'), FieldFactory.getFieldBySymbol(23, 12, 'R'),
                FieldFactory.getFieldBySymbol(23, 13, 'R'), FieldFactory.getFieldBySymbol(23, 14, 'R'),
                FieldFactory.getFieldBySymbol(23, 15, 'R'),
                FieldFactory.getFieldBySymbol(24, 11, 'R'), FieldFactory.getFieldBySymbol(24, 12, 'R'),
                FieldFactory.getFieldBySymbol(24, 13, 'R'), FieldFactory.getFieldBySymbol(24, 14, 'R'),
                FieldFactory.getFieldBySymbol(24, 15, 'R'),
                FieldFactory.getFieldBySymbol(25, 11, 'R'), FieldFactory.getFieldBySymbol(25, 12, 'R'),
                FieldFactory.getFieldBySymbol(25, 13, 'R'), FieldFactory.getFieldBySymbol(25, 14, 'R'),
                FieldFactory.getFieldBySymbol(25, 15, 'R')
        ));
    }

    private HashSet<Field> getWorkroomExitFields() {
        return new HashSet<>(Arrays.asList(
                FieldFactory.getFieldBySymbol(20, 11, 'R'),
                FieldFactory.getFieldBySymbol(20, 12, 'R'),
                FieldFactory.getFieldBySymbol(20, 14, 'R'),
                FieldFactory.getFieldBySymbol(20, 15, 'R')
        ));
    }
}