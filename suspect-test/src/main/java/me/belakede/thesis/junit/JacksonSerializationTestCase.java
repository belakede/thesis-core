package me.belakede.thesis.junit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public abstract class JacksonSerializationTestCase<T> extends TestCase {

    private final Class<T> type;
    private ObjectMapper objectMapper;

    public JacksonSerializationTestCase(String name, Class<T> type) {
        super(name);
        this.type = type;
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
        T actual = toObject(expectedJson());
        assertThat(actual, is(expected));
    }

    private String toJson(T t) throws JsonProcessingException {
        return objectMapper.writeValueAsString(t);
    }

    private T toObject(String json) throws IOException {
        return objectMapper.readValue(json, type);
    }

}
