package me.belakede.thesis.junit.me.belakede.thesis.openpojo.rule;

import com.openpojo.reflection.PojoClass;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.Serializable;

import static org.junit.Assert.*;


public class ImplementsSerializableRuleTest {

    ImplementsSerializableRule testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new ImplementsSerializableRule();
    }

    @Test
    public void testEvaluateShouldFailWhenClassDoNotImplementsSerializableInterface() {
        PojoClass pojo = Mockito.mock(PojoClass.class);
        Mockito.when(pojo.extendz(Serializable.class)).thenReturn(false);
        try {
            testSubject.evaluate(pojo);
        } catch (AssertionError ex) {
            assertThat(ex.getMessage(), Matchers.is(testSubject.FAILED_MESSAGE));
        }
    }

    @Test
    public void testEvaluateShouldPassWhenClassImplementsSerializableInterface() {
        PojoClass pojo = Mockito.mock(PojoClass.class);
        Mockito.when(pojo.extendz(Serializable.class)).thenReturn(true);
        testSubject.evaluate(pojo);
    }

}