package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.board.Board;
import me.belakede.thesis.game.board.BoardType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class BoardsTest {

    @Test
    public void testGetDefaultBoardShouldReturnWithTheDefaultBoard() throws Exception {
        Board actual = Boards.getDefaultBoard();
        assertTrue(BoardType.DEFAULT.equals(actual.getBoardType()));
        assertEquals(6, actual.getStartingFields().size());
        assertEquals(9, actual.getRoomFields().size());
    }

    @Test(expected = Exception.class)
    public void getAdvancedBoard() throws Exception {
        Boards.getAdvancedBoard();
    }

}