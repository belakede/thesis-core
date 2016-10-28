package me.belakede.thesis.junit.me.belakede.thesis.openpojo.rule;

import com.openpojo.log.utils.MessageFormatter;
import com.openpojo.reflection.PojoClass;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.rule.Rule;

import java.util.ArrayList;
import java.util.List;

public class NoFieldShadowingExceptSerialVersionUIDRule implements Rule {

    @Override
    public void evaluate(PojoClass pojoClass) {
        PojoClass parentPojoClass;
        List<String> parentFields = new ArrayList<>();

        parentPojoClass = pojoClass.getSuperClass();
        do {
            parentPojoClass.getPojoFields().stream()
                    .filter(pf -> !(pf.isPrivate() && pf.isStatic() && pf.isFinal() && "serialVersionUID".equalsIgnoreCase(pf.getName())))
                    .map(pf -> pf.getName())
                    .forEach(parentFields::add);
            parentPojoClass = parentPojoClass.getSuperClass();
        } while (parentPojoClass != null);
        pojoClass.getPojoFields().stream().filter(pf -> parentFields.contains(pf.getName())).forEach(pf -> {
            Affirm.fail(MessageFormatter.format("Field=[{0}] shadows field with the same name in parent class=[{1}]", new Object[]{pf, parentFields}));
        });
    }

}
