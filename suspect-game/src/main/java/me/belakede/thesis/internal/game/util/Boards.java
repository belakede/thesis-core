package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.board.Board;
import me.belakede.thesis.game.board.BoardType;
import me.belakede.thesis.internal.game.importer.MapLoader;

public class Boards {

    public static Board getDefaultBoard() throws Exception {
        return getBoardByType(BoardType.DEFAULT);
    }

    public static Board getAdvancedBoard() throws Exception {
        return getBoardByType(BoardType.ADVANCED);
    }

    private static Board getBoardByType(BoardType boardType) throws Exception {
        return new MapLoader(boardType).load();
    }

}
