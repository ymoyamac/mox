package com.aey.mox.uni;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.aey.mox.core.Prop;
import com.aey.mox.listeners.Observe;

public class ContextTest {

    private Context<String, String> context;

    @BeforeEach
    void setUp() {
        context = new Context<>();
    }

    @Test
    void testSetAndGetProp() {
        context.set("username", "john");

        Optional<String> value = context.get("username");

        assertTrue(value.isPresent());
        assertEquals("john", value.get());

        // Ensure props map contains it
        Map<String, Prop<?>> props = context.getProps();
        assertTrue(props.containsKey("username"));
    }

    @Test
    void testSetMultipleProps() {
        Prop<String> prop1 = Prop.bind("k1", "v1");
        Prop<Integer> prop2 = Prop.bind("k2", 123);

        context.set(prop1, prop2);

        assertEquals("v1", context.get("k1").orElseThrow());
        assertEquals(123, context.get("k2").orElseThrow());
    }

    @Test
    void testGetNonExistentPropReturnsEmpty() {
        Optional<String> value = context.get("doesNotExist");
        assertTrue(value.isEmpty());
    }

    @Test
    void testErrSetterAndReturn() {
        String result = context.err("Some error");
        assertEquals("Some error", result);
    }

    @Test
    void testSubscribeAndEmit() {
        Observe mockListener = mock(Observe.class);
        context.subscribe("event1", mockListener);

        context.emit("event1", "payload");

        verify(mockListener, times(1)).update("payload");
    }

    @Test
    void testUnsubscribe() {
        Observe mockListener = mock(Observe.class);
        context.subscribe("event2", mockListener);
        context.unsubscribe("event2");

        // After unsubscribe, emit should do nothing (no exception, no update)
        context.emit("event2", "payload");

        verify(mockListener, never()).update(any());
    }
}
