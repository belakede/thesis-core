package me.belakede.thesis.junit.me.belakede.thesis.openpojo.rule;

import com.openpojo.reflection.PojoClass;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.rule.Rule;

import java.io.Serializable;

public class ImplementsSerializableRule implements Rule {

    static final String FAILED_MESSAGE = "Pojo class must implements serializable interface.";

    @Override
    public void evaluate(PojoClass pojoClass) {
        if (!pojoClass.extendz(Serializable.class)) {
            Affirm.fail(FAILED_MESSAGE);
        }
    }
}
