package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.junit.ExtendedEqualsHashCodeTestCase;
import me.belakede.thesis.junit.PojoClassTestCase;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class CoordinateTest {

    public static final class CoordinateEqualsHashCodeTest extends ExtendedEqualsHashCodeTestCase {
        public CoordinateEqualsHashCodeTest(String name) {
            super(name, Coordinate.class, new ArrayList<>());
        }

        @Override
        protected Coordinate createInstance() throws Exception {
            return new Coordinate(1, 2);
        }

        @Override
        protected Coordinate createNotEqualInstance() throws Exception {
            return new Coordinate(5, 6);
        }
    }

    public static final class CoordinatePojoTest extends PojoClassTestCase {
        public CoordinatePojoTest(String name) {
            super(name, Coordinate.class);
        }
    }

}