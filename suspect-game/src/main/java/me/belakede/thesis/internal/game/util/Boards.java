package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.board.Board;
import me.belakede.thesis.game.equipment.BoardType;
import me.belakede.thesis.internal.game.importer.MapLoader;

import java.io.IOException;

public class Boards {

    private Boards() {
    }

    public static Board getDefaultBoard() throws IOException {
        return getBoardByType(BoardType.DEFAULT);
    }

    public static Board getAdvancedBoard() throws IOException {
        return getBoardByType(BoardType.ADVANCED);
    }

    public static Board getBoardByType(BoardType boardType) throws IOException {
        return new MapLoader(boardType).load();
    }

}
