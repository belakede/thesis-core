package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.board.FieldType;
import me.belakede.thesis.game.board.RoomField;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FieldMatrixTest {

    @Test
    public void testIsAvailableShouldReturnTrueWhenToFieldDistanceIsLowerThanTwelve() throws Exception {
        // GIVEN
        Field[][] matrix = getFieldMatrix(7);
        // WHEN
        FieldMatrix testSubject = new FieldMatrix(7, matrix);
        Field from = matrix[0][0];
        Field to = matrix[6][6];
        // THEN
        boolean actual = testSubject.isAvailable(from, to);
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsAvailableShouldReturnTrueWhenToFieldEqualsFromField() throws Exception {
        // GIVEN
        Field[][] matrix = getFieldMatrix(1);
        // WHEN
        FieldMatrix testSubject = new FieldMatrix(1, matrix);
        Field from = matrix[0][0];
        Field to = matrix[0][0];
        // THEN
        boolean actual = testSubject.isAvailable(from, to);
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsAvailableShouldReturnFalseWhenToFieldDistanceIsGreaterThanTwelve() throws Exception {
        // GIVEN
        Field[][] matrix = getFieldMatrix(8);
        // WHEN
        FieldMatrix testSubject = new FieldMatrix(8, matrix);
        Field from = matrix[0][0];
        Field to = matrix[7][6];
        // THEN
        boolean actual = testSubject.isAvailable(from, to);
        Assert.assertFalse(actual);
    }

    @Test
    public void testGetAvailableFieldsShouldReturnSetOfNeighbourFieldsWhenStepIsOne() throws Exception {
        // GIVEN
        Field[][] matrix = getFieldMatrix(3);
        // WHEN
        FieldMatrix testSubject = new FieldMatrix(3, matrix);
        int step = 1;
        Field from = matrix[1][1];
        // THEN
        Set<Field> expected = new HashSet<>(Arrays.asList(matrix[0][1], matrix[1][0], matrix[1][1], matrix[1][2], matrix[2][1]));
        Set<Field> actual = testSubject.getAvailableFields(from, step);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAvailableFieldsShouldReturnAllFieldsWhenFromIsTheCenterOfAThreeTimesThreeMatrixAndStepIsTwo() throws Exception {
        // GIVEN
        Field[][] matrix = getFieldMatrix(3);
        // WHEN
        FieldMatrix testSubject = new FieldMatrix(3, matrix);
        int step = 2;
        Field from = matrix[1][1];
        // THEN
        Set<Field> expected = new HashSet<>(Arrays.asList(
                matrix[0][0], matrix[0][1], matrix[0][2],
                matrix[1][0], matrix[1][1], matrix[1][2],
                matrix[2][0], matrix[2][1], matrix[2][2]));
        Set<Field> actual = testSubject.getAvailableFields(from, step);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetRoomFieldsShouldReturnTheOnlyOneRoomFieldOnBoard() throws Exception {
        // GIVEN
        RoomField roomField = Mockito.mock(RoomField.class);
        Field[][] matrix = getFieldMatrix(3);
        // WHEN
        matrix[1][1] = roomField;
        FieldMatrix testSubject = new FieldMatrix(3, matrix);
        // THEN
        List<Field> expected = Arrays.asList(roomField);
        List<Field> actual = testSubject.getRoomFields();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetRoomFieldsShouldReturnAllRoomFieldsOnBoard() throws Exception {
        // GIVEN
        RoomField firstRoomField = Mockito.mock(RoomField.class);
        RoomField secondRoomField = Mockito.mock(RoomField.class);
        RoomField thirdRoomField = Mockito.mock(RoomField.class);
        RoomField fourthRoomField = Mockito.mock(RoomField.class);
        Field[][] matrix = getFieldMatrix(5);
        // WHEN
        matrix[1][0] = firstRoomField;
        matrix[3][2] = secondRoomField;
        matrix[3][3] = thirdRoomField;
        matrix[4][4] = fourthRoomField;
        FieldMatrix testSubject = new FieldMatrix(3, matrix);
        // THEN
        List<Field> expected = Arrays.asList(firstRoomField, secondRoomField, thirdRoomField, fourthRoomField);
        List<Field> actual = testSubject.getRoomFields();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetStartingFieldsShouldReturnTheOnlyOneStartingFieldOnBoard() throws Exception {
        // GIVEN
        Field[][] matrix = getFieldMatrix(2);
        Field field = matrix[1][1];
        // WHEN
        Mockito.when(field.getFieldType()).thenReturn(FieldType.START);
        FieldMatrix testSubject = new FieldMatrix(3, matrix);
        // THEN
        Mockito.verify(field).getFieldType();
        Set<Field> expected = new HashSet<>(Arrays.asList(field));
        Set<Field> actual = testSubject.getStartingFields();

        Assert.assertEquals(1, actual.size());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetStartingFieldsShouldReturnAllStartingFieldsOnBoard() throws Exception {
        // GIVEN
        Field[][] matrix = getFieldMatrix(2);
        Field firstField = matrix[0][0];
        Field secondField = matrix[1][1];
        // WHEN
        Mockito.when(firstField.getFieldType()).thenReturn(FieldType.START);
        Mockito.when(secondField.getFieldType()).thenReturn(FieldType.START);
        FieldMatrix testSubject = new FieldMatrix(3, matrix);
        // THEN
        Mockito.verify(firstField).getFieldType();
        Mockito.verify(secondField).getFieldType();
        Set<Field> expected = new HashSet<>(Arrays.asList(firstField, secondField));
        Set<Field> actual = testSubject.getStartingFields();

        Assert.assertEquals(2, actual.size());
        Assert.assertEquals(expected, actual);
    }


    private Field[][] getFieldMatrix(int size) {
        Field[][] matrix = new Field[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Mockito.mock(Field.class);
            }
        }
        return matrix;
    }

}