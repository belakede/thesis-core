package me.belakede.thesis.internal.game.importer;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.belakede.thesis.game.board.Board;
import me.belakede.thesis.game.equipment.BoardType;
import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.internal.game.board.DefaultBoard;
import me.belakede.thesis.internal.game.board.FieldFactory;
import me.belakede.thesis.internal.game.importer.domain.RoomDescription;
import me.belakede.thesis.internal.game.importer.transformer.DescriptorTransformer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {

    private static final String BOARD_PATH = "game/board/";
    private static final String MAP_EXTENSION = ".map";
    private static final String ROOM_DESCRIPTOR_EXTENSION = ".rdesc";

    private final BoardType boardType;


    public MapLoader(BoardType boardType) {
        this.boardType = boardType;
    }

    public Board load() throws IOException {
        Field[][] fields = loadFields();
        RoomDescription roomDescription = loadRoomDescription();
        DescriptorTransformer transformer = new DescriptorTransformer(fields, roomDescription);
        return new DefaultBoard(boardType, fields, transformer.getRoomFields(), transformer.getSecretPassages());
    }

    private Field[][] loadFields() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(BOARD_PATH + boardType.toString() + MAP_EXTENSION);
        Field[][] fields = new Field[boardType.getSize()][boardType.getSize()];
        Scanner scanner = new Scanner(inputStream);
        int row = 0;
        while (scanner.hasNextLine()) {
            char[] chars = scanner.nextLine().toCharArray();
            for (int column = 0; column < chars.length; column++) {
                fields[row][column] = FieldFactory.getFieldBySymbol(row, column, chars[column]);
            }
            row = row + 1;
        }
        return fields;
    }

    private RoomDescription loadRoomDescription() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(BOARD_PATH + boardType.toString() + ROOM_DESCRIPTOR_EXTENSION);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputStream, RoomDescription.class);
    }

}
