package com.aey.mox.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ContextTest {

    @Mock
    Context<String, Exception> context;

    @BeforeEach
    public void init() {
        this.context = new Context<>();
        this.context.set("userId", "uf8j0_dj1234");
        this.context.set(Prop.bind("code", 0b11010));
    }

    @Test
    @DisplayName("To string")
    void testToString() {
        Context<String, Exception> context = new Context<>();
        String expected = "Context {props={}, actions=null, tableListeners={}, err=null}";
        assertEquals(expected, context.toString());
    }

    @Test
    @DisplayName("Set action")
    void testSetActions() {
        Context<String, Exception> context = new Context<>("set");
        assertTrue(Arrays.asList(context.actions()).contains("set"));
    }

    @Test
    @DisplayName("Test get prop")
    void testGetProp() {
        Optional<String> prop = context.get("userId");
        assertTrue(prop.isPresent());
        assertEquals("uf8j0_dj1234", prop.get());
    }

    @Test
    @DisplayName("Prop not found")
    void testPropNotFound() {
        Optional<String> prop = context.get("non-existent");
        assertTrue(prop.isEmpty());
    }

    @Test
    @DisplayName("Bind prop")
    void testBindProp() {
        assertTrue(this.context.get("code").isPresent());
        assertEquals(0b11010, this.context.get("code").get());
    }

    @Test
    @DisplayName("Get props")
    void testPropsNotEmpty() {
        assertFalse(this.context.getProps().isEmpty());
    }

    @Test
    @DisplayName("Bind prop")
    void testErrorAssignment() {
        Context<String, Exception> ctx = new Context<>();
        Exception err = new IllegalArgumentException("Invalid input");
        Exception returned = ctx.err(err);
        assertEquals(err, returned);
    }

}
