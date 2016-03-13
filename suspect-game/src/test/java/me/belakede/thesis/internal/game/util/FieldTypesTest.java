package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.board.FieldType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class FieldTypesTest {

    @Test
    public void testFindFieldTypeBySymbolShouldReturnRoomWhenSymbolIsR() throws Exception {
        assertEquals(FieldType.ROOM, FieldTypes.findFieldTypeBySymbol('R'));
    }

    @Test
    public void testFindFieldTypeBySymbolShouldReturnSimpleWhenSymbolIsF() throws Exception {
        assertEquals(FieldType.SIMPLE, FieldTypes.findFieldTypeBySymbol('F'));
    }

    @Test
    public void testFindFieldTypeBySymbolShouldReturnWallWhenSymbolIsW() throws Exception {
        assertEquals(FieldType.WALL, FieldTypes.findFieldTypeBySymbol('W'));
    }

    @Test
    public void testFindFieldTypeBySymbolShouldReturnRoomWhenSymbolIsE() throws Exception {
        assertEquals(FieldType.END, FieldTypes.findFieldTypeBySymbol('E'));
    }

    @Test
    public void testFindFieldTypeBySymbolShouldReturnNullWhenSymbolIsN() throws Exception {
        assertEquals(FieldType.NULL, FieldTypes.findFieldTypeBySymbol('N'));
    }

    @Test
    public void testFindFieldTypeBySymbolShouldReturnStartWhenSymbolIsS() throws Exception {
        assertEquals(FieldType.START, FieldTypes.findFieldTypeBySymbol('S'));
    }

}