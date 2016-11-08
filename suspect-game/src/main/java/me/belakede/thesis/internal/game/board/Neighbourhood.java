package me.belakede.thesis.internal.game.board;

import me.belakede.thesis.game.field.Field;
import me.belakede.thesis.game.field.FieldType;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final class Neighbourhood {

    private static final Set<FieldType> invalidFieldTypes;

    static {
        invalidFieldTypes = new HashSet<>();
        invalidFieldTypes.add(FieldType.NULL);
        invalidFieldTypes.add(FieldType.WALL);
    }

    private final Field top;
    private final Field right;
    private final Field bottom;
    private final Field left;
    private final Set<Field> neighbours;


    public Neighbourhood(Field top, Field right, Field bottom, Field left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
        this.neighbours = new HashSet<>();
    }

    public Set<Field> getNeighbours() {
        if (neighbours.isEmpty()) {
            addNeighbours();
        }
        return Collections.unmodifiableSet(neighbours);
    }

    private void addNeighbours() {
        add(top);
        add(right);
        add(bottom);
        add(left);
    }

    private void add(Field field) {
        if (!isNull(field) && isValid(field)) {
            neighbours.add(field);
        }
    }

    private boolean isNull(Field field) {
        return field == null;
    }

    private boolean isValid(Field field) {
        return !invalidFieldTypes.contains(field.getFieldType());
    }

}
