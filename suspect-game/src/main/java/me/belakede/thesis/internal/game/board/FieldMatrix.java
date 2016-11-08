package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.field.Field;
import me.belakede.thesis.game.field.FieldType;

import java.util.*;

class FieldMatrix {

    public static final int MAX_DISTANCE = 12;

    private final int size;
    private final Field[][] fields;
    private final Set<Field> startingFields;
    private final Map<Field, Neighbourhood> fieldNeighbourhoodMap;

    public FieldMatrix(FieldMatrix matrix) {
        this(matrix.size, matrix.fields);
    }

    public FieldMatrix(int size, Field[][] fields) {
        this.size = size;
        this.fields = fields;
        this.startingFields = new HashSet<>();
        this.fieldNeighbourhoodMap = new HashMap<>();
        initStartingFields();
        initFieldNeighbourhoodMap();
    }

    public Field getField(int row, int column) {
        return fields[row][column];
    }

    public Set<Field> getStartingFields() {
        return Collections.unmodifiableSet(startingFields);
    }

    public boolean isAvailable(Field from, Field to) {
        return getAvailableFields(from).contains(to);
    }

    public Set<Field> getAvailableFields(Field from) {
        return getAvailableFields(from, MAX_DISTANCE);
    }

    public Set<Field> getAvailableFields(Field from, int distance) {
        Map<Field, Integer> visited = new HashMap<>();
        return getAvailableFields(from, visited, distance);
    }

    private Set<Field> getAvailableFields(Field from, Map<Field, Integer> visited, int distance) {
        Set<Field> result = new HashSet<>();
        if (distance >= 0) {
            result.add(from);
            visited.put(from, distance);
            Set<Field> neighbours = fieldNeighbourhoodMap.get(from).getNeighbours();
            neighbours.stream()
                    .filter(f -> !visited.containsKey(f) || visited.get(f) < distance)
                    .map(f -> getAvailableFields(f, visited, distance - 1))
                    .forEach(result::addAll);
        }
        return result;
    }

    private void initStartingFields() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (FieldType.START.equals(fields[i][j].getFieldType())) {
                    startingFields.add(fields[i][j]);
                }
            }
        }
    }

    private void initFieldNeighbourhoodMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!FieldType.WALL.equals(fields[i][j].getFieldType())) {
                    fieldNeighbourhoodMap.put(fields[i][j], getNeighbourhood(i, j));
                }
            }
        }
    }

    private Neighbourhood getNeighbourhood(int row, int column) {
        Field top = row == 0 ? null : fields[row - 1][column];
        Field right = column == size - 1 ? null : fields[row][column + 1];
        Field bottom = row == size - 1 ? null : fields[row + 1][column];
        Field left = column == 0 ? null : fields[row][column - 1];
        return new Neighbourhood(top, right, bottom, left);
    }

}
