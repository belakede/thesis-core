package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.board.FieldType;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;


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
        assertTrue(actual);
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
        assertTrue(actual);
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
        assertFalse(actual);
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
        assertEquals(expected, actual);
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
        assertEquals(expected, actual);
    }

    @Test
    public void testGetStartingFieldsShouldReturnTheOnlyOneStartingFieldOnBoard() throws Exception {
        // GIVEN
        Field[][] matrix = getFieldMatrix(2);
        Field field = matrix[1][1];
        // WHEN
        Mockito.when(field.getFieldType()).thenReturn(FieldType.START);
        FieldMatrix testSubject = new FieldMatrix(2, matrix);
        // THEN
        Mockito.verify(field, Mockito.atLeastOnce()).getFieldType();
        Set<Field> expected = new HashSet<>(Arrays.asList(field));
        Set<Field> actual = testSubject.getStartingFields();

        assertEquals(1, actual.size());
        assertEquals(expected, actual);
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
        FieldMatrix testSubject = new FieldMatrix(2, matrix);
        // THEN
        Mockito.verify(firstField, Mockito.atLeastOnce()).getFieldType();
        Mockito.verify(secondField, Mockito.atLeastOnce()).getFieldType();
        Set<Field> expected = new HashSet<>(Arrays.asList(firstField, secondField));
        Set<Field> actual = testSubject.getStartingFields();

        assertEquals(2, actual.size());
        assertEquals(expected, actual);
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