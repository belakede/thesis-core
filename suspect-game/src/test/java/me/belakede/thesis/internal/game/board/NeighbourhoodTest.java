package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.board.FieldType;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class NeighbourhoodTest {

    @Test
    public void testGetNeighboursShouldReturnAllFourNeighbourWhenEveryNeighbourIsValidAndNotNull() throws Exception {
        // GIVEN
        Field top = Mockito.mock(Field.class);
        Field right = Mockito.mock(Field.class);
        Field bottom = Mockito.mock(Field.class);
        Field left = Mockito.mock(Field.class);
        Neighbourhood neighbourhood = new Neighbourhood(top, right, bottom, left);
        // WHEN
        Mockito.when(top.getFieldType()).thenReturn(FieldType.SIMPLE);
        Mockito.when(right.getFieldType()).thenReturn(FieldType.SIMPLE);
        Mockito.when(bottom.getFieldType()).thenReturn(FieldType.SIMPLE);
        Mockito.when(left.getFieldType()).thenReturn(FieldType.SIMPLE);
        Set<Field> actual = neighbourhood.getNeighbours();
        // THEN
        Mockito.verify(top).getFieldType();
        Mockito.verify(right).getFieldType();
        Mockito.verify(bottom).getFieldType();
        Mockito.verify(left).getFieldType();
        Set<Field> expected = new HashSet<>(Arrays.asList(top, right, bottom, left));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNeighboursShouldReturnOnlyTheRightFieldWhenOtherNeighboursAreNull() throws Exception {
        // GIVEN
        Field right = Mockito.mock(Field.class);
        Neighbourhood neighbourhood = new Neighbourhood(null, right, null, null);
        // WHEN
        Mockito.when(right.getFieldType()).thenReturn(FieldType.SIMPLE);
        Set<Field> actual = neighbourhood.getNeighbours();
        // THEN
        Mockito.verify(right).getFieldType();
        Set<Field> expected = new HashSet<>(Arrays.asList(right));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNeighboursShouldReturnOnlyTheBottomFieldWhenOtherNeighboursAreInvalid() throws Exception {
        // GIVEN
        Field top = Mockito.mock(Field.class);
        Field right = Mockito.mock(Field.class);
        Field bottom = Mockito.mock(Field.class);
        Field left = Mockito.mock(Field.class);
        Neighbourhood neighbourhood = new Neighbourhood(top, right, bottom, left);
        // WHEN
        Mockito.when(top.getFieldType()).thenReturn(FieldType.WALL);
        Mockito.when(right.getFieldType()).thenReturn(FieldType.NULL);
        Mockito.when(bottom.getFieldType()).thenReturn(FieldType.SIMPLE);
        Mockito.when(left.getFieldType()).thenReturn(FieldType.WALL);
        Set<Field> actual = neighbourhood.getNeighbours();
        // THEN
        Mockito.verify(top).getFieldType();
        Mockito.verify(right).getFieldType();
        Mockito.verify(bottom).getFieldType();
        Mockito.verify(left).getFieldType();
        Set<Field> expected = new HashSet<>(Arrays.asList(bottom));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNeighboursShouldReturnOnlyTheLeftAndRightFieldWhenTopIsNullAndBottomIsInvalid() throws Exception {
        Field right = Mockito.mock(Field.class);
        Field bottom = Mockito.mock(Field.class);
        Field left = Mockito.mock(Field.class);
        Neighbourhood neighbourhood = new Neighbourhood(null, right, bottom, left);
        // WHEN
        Mockito.when(right.getFieldType()).thenReturn(FieldType.SIMPLE);
        Mockito.when(bottom.getFieldType()).thenReturn(FieldType.WALL);
        Mockito.when(left.getFieldType()).thenReturn(FieldType.SIMPLE);
        Set<Field> actual = neighbourhood.getNeighbours();
        // THEN
        Mockito.verify(right).getFieldType();
        Mockito.verify(bottom).getFieldType();
        Mockito.verify(left).getFieldType();
        Set<Field> expected = new HashSet<>(Arrays.asList(right, left));
        assertEquals(expected, actual);
    }
}