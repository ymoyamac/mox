package com.aey.mox.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ActionsTest {
    @Test
    void testPublishActionValue() {
        assertEquals("publish", Actions.PUBLISH.getAction());
    }

    @Test
    void testToStringEqualsName() {
        assertEquals("PUBLISH", Actions.PUBLISH.toString());
    }
}
