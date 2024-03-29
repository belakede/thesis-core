package me.belakede.thesis.junit;

import junitx.extensions.EqualsHashCodeTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class ExtendedEqualsHashCodeTestCase<T> extends EqualsHashCodeTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtendedEqualsHashCodeTestCase.class);
    private final Class<T> typeClass;
    private final Set<String> nullableFields;

    public ExtendedEqualsHashCodeTestCase(String name, Class<T> typeClass, Collection<String> nullableFields) {
        super(name);
        this.typeClass = typeClass;
        this.nullableFields = new HashSet<>(nullableFields);
    }

    public final void testEqualsAgainstDifferentType() throws Exception {
        Object object = new Object();
        Object instance = createInstance();
        junitx.framework.Assert.assertNotEquals(instance, object);
    }

    public final void testEqualsAgainstNullableFields() throws Exception {
        nullableFields.forEach(f -> {
            try {
                T instance = (T) createInstance();
                T secondInstance = (T) createInstance();
                Field field;
                try {
                    field = typeClass.getDeclaredField(f);
                } catch (NoSuchFieldException ex) {
                    LOGGER.info("testEqualsAgainstNullableFields: not found. Checking superclass");
                    field = typeClass.getSuperclass().getDeclaredField(f);
                }
                field.setAccessible(true);
                field.set(instance, null);
                junitx.framework.Assert.assertNotEquals(instance, secondInstance);
                junitx.framework.Assert.assertNotEquals(secondInstance, instance);
                field.set(secondInstance, null);
                junitx.framework.Assert.assertEquals(instance, secondInstance);
                junitx.framework.Assert.assertEquals(secondInstance, instance);
            } catch (Exception e) {
                LOGGER.info("testEqualsAgainstNullableFields failed: {}", e);
            }
        });
    }

    public final void testHashCodeAgainstNullableFields() throws Exception {
        nullableFields.forEach(f -> {
            try {
                T instance = (T) createInstance();
                T secondInstance = (T) createInstance();
                Field field;
                try {
                    field = typeClass.getDeclaredField(f);
                } catch (NoSuchFieldException ex) {
                    LOGGER.info("testHashCodeAgainstNullableFields: not found. Checking superclass");
                    field = typeClass.getSuperclass().getDeclaredField(f);
                }
                field.setAccessible(true);
                field.set(instance, null);
                junitx.framework.Assert.assertNotEquals(instance.hashCode(), secondInstance.hashCode());
                junitx.framework.Assert.assertNotEquals(secondInstance.hashCode(), instance.hashCode());
                field.set(secondInstance, null);
                junitx.framework.Assert.assertEquals(instance, secondInstance);
                junitx.framework.Assert.assertEquals(secondInstance, instance);
            } catch (Exception e) {
                LOGGER.info("testHashCodeAgainstNullableFields failed: {}", e);
            }
        });
    }


}
