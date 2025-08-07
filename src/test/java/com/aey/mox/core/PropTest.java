package com.aey.mox.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PropTest {
    @Test
    void testBindCreatesInstanceWithCorrectValues() {
        Prop<String> prop = Prop.bind("username", "admin");

        assertEquals("username", prop.getId());
        assertEquals("admin", prop.getValue());
        assertEquals(String.class, prop.getClazz());
    }

    @Test
    void testSetIdUpdatesValue() {
        Prop<String> prop = Prop.bind("id", "value");
        prop.setId("newId");

        assertEquals("newId", prop.getId());
    }

    @Test
    void testSetValueUpdatesValue() {
        Prop<Integer> prop = Prop.bind("age", 20);
        prop.setValue(30);

        assertEquals(30, prop.getValue());
    }

    @Test
    void testToStringContainsValues() {
        Prop<Double> prop = Prop.bind("score", 99.5);
        String result = prop.toString();

        assertTrue(result.contains("score"));
        assertTrue(result.contains("99.5"));
        assertTrue(result.contains("Double"));
    }
}
