package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.board.Field;
import me.belakede.thesis.game.board.FieldType;
import me.belakede.thesis.game.board.RoomField;

import java.io.Serializable;
import java.util.*;

final class FieldMatrix implements Serializable {

    public static final int MAX_DISTANCE = 12;

    private final int size;
    private final Field[][] fields;
    private transient List<Field> roomFields;
    private transient Set<Field> startingFields;
    private transient Map<Field, IndexPair> indices;

    public FieldMatrix(FieldMatrix another) {
        this(another.size, another.fields);
    }

    public FieldMatrix(int size, Field[][] fields) {
        this.size = size;
        this.fields = fields;
        this.indices = new HashMap<>();
        this.roomFields = new ArrayList<>();
        this.startingFields = new HashSet<>();
        uploadHelperFields();
    }

    public List<Field> getRoomFields() {
        checkHelpers();
        return new ArrayList<>(roomFields);
    }

    private void checkHelpers() {
        if (indices == null) {
            uploadHelperFields();
        }
    }

    public Set<Field> getStartingFields() {
        checkHelpers();
        return new HashSet<>(startingFields);
    }

    public boolean isAvailable(Field from, Field to) {
        checkHelpers();
        return indices.get(from).getDistance(indices.get(to)) <= MAX_DISTANCE;
    }

    public Set<Field> getAvailableFields(Field from, int step) {
        checkHelpers();
        Set<Field> availableFields = new HashSet<>();
        IndexPair indexPair = indices.get(from);
        int startRowIndex = Math.max(indexPair.getRow() - step - 1, 0);
        int startColumnIndex = Math.max(indexPair.getColumn() - step - 1, 0);
        int endRowIndex = Math.min(indexPair.getRow() + step + 1, size);
        int endColumnIndex = Math.min(indexPair.getColumn() + step + 1, size);
        for (int i = startRowIndex; i < endRowIndex; i++) {
            for (int j = startColumnIndex; j < endColumnIndex; j++) {
                if (indexPair.getDistance(i, j) <= step) {
                    availableFields.add(fields[i][j]);
                }
            }
        }
        return availableFields;
    }

    private void uploadHelperFields() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                indices.put(fields[i][j], new IndexPair(i, j));
                if (FieldType.START.equals(fields[i][j].getFieldType())) {
                    startingFields.add(fields[i][j]);
                } else if (fields[i][j] instanceof RoomField) {
                    roomFields.add(fields[i][j]);
                }
            }
        }
    }

    private static final class IndexPair {
        private final int row;
        private final int column;

        public IndexPair(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        public int getDistance(IndexPair other) {
            return Math.abs(row - other.row) + Math.abs(column - other.column);
        }

        public int getDistance(int otherRow, int otherColumn) {
            return Math.abs(row - otherRow) + Math.abs(column - otherColumn);
        }
    }

}
