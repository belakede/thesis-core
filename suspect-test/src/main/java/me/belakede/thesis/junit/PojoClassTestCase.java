package me.belakede.thesis.junit;

import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.*;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import junit.framework.TestCase;
import me.belakede.thesis.junit.me.belakede.thesis.openpojo.rule.ImplementsSerializableRule;
import org.junit.Test;

public abstract class PojoClassTestCase<T> extends TestCase {

    private final Class<T> typeClass;

    public PojoClassTestCase(String name, Class<T> typeClass) {
        super(name);
        this.typeClass = typeClass;
    }

    @Test
    public void testPojoMustImplementsSerializable() {
        Validator validator = ValidatorBuilder.create().with(new ImplementsSerializableRule()).build();
        validator.validate(PojoClassFactory.getPojoClass(typeClass));
    }

    @Test
    public void testPojoMostHaveSerialVersionUID() {
        Validator validator = ValidatorBuilder.create().with(new SerializableMustHaveSerialVersionUIDRule()).build();
        validator.validate(PojoClassFactory.getPojoClass(typeClass));
    }

    @Test
    public void testPojoShouldNotHavePublicFields() {
        Validator validator = ValidatorBuilder.create().with(new NoPublicFieldsRule()).build();
        validator.validate(PojoClassFactory.getPojoClass(typeClass));
    }

    @Test
    public void testPojoShouldNotHaveStaticFieldExceptFinal() {
        Validator validator = ValidatorBuilder.create().with(new NoStaticExceptFinalRule()).build();
        validator.validate(PojoClassFactory.getPojoClass(typeClass));
    }

    @Test
    public void testPojoShouldNotShadowingFields() {
        Validator validator = ValidatorBuilder.create().with(new NoFieldShadowingRule()).build();
        validator.validate(PojoClassFactory.getPojoClass(typeClass));
    }

    @Test
    public void testPojoGetterMustExists() {
        Validator validator = ValidatorBuilder.create().with(new GetterMustExistRule()).build();
        validator.validate(PojoClassFactory.getPojoClass(typeClass));
    }

    @Test
    public void testPojoSetterMustExists() {
        Validator validator = ValidatorBuilder.create().with(new SetterMustExistRule()).build();
        validator.validate(PojoClassFactory.getPojoClass(typeClass));
    }

    @Test
    public void testGetters() {
        Validator validator = ValidatorBuilder.create().with(new GetterTester()).build();
        validator.validate(PojoClassFactory.getPojoClass(typeClass));
    }

    @Test
    public void testSetters() {
        Validator validator = ValidatorBuilder.create().with(new SetterTester()).build();
        validator.validate(PojoClassFactory.getPojoClass(typeClass));
    }

}
