package com.aey.mox.core;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ContextTest {
    @Test
    void testSetAndGetValue() {
        Context<String, Exception> ctx = new Context<>();
        ctx.set("username", "chatgpt");

        Optional<String> result = ctx.get("username");
        assertTrue(result.isPresent());
        assertEquals("chatgpt", result.get());
    }

    @Test
    void testErrorAssignment() {
        Context<String, Exception> ctx = new Context<>();
        Exception err = new IllegalArgumentException("Invalid input");
        Exception returned = ctx.err(err);

        assertEquals(err, returned);
    }
}
