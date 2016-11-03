package me.belakede.thesis.junit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public abstract class JacksonSerializationTestCase<T> extends TestCase {

    private ObjectMapper objectMapper;

    public JacksonSerializationTestCase(String name) {
        super(name);
        this.objectMapper = new ObjectMapper();
    }

    public abstract T expectedObject();

    public abstract String expectedJson();

    public final void testJacksonSerialization() throws Exception {
        String expected = expectedJson();
        String actual = toJson(expectedObject());
        assertThat(actual, is(expected));
    }

    public final void testJacksonDeserialization() throws Exception {
        T expected = expectedObject();
        T actual = toObject(expectedJson(), (Class<T>) expected.getClass());
        assertThat(actual, is(expected));
    }

    private String toJson(T t) throws JsonProcessingException {
        return objectMapper.writeValueAsString(t);
    }

    private T toObject(String json, Class<T> type) throws IOException {
        return objectMapper.readValue(json, type);
    }

}
