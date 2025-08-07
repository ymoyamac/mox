package com.aey.mox.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.aey.mox.listeners.EventListener;

public class ListenerTest {
    private EventListener mockListener;
    private Listener listener;

    @BeforeEach
    void setUp() {
        mockListener = Mockito.mock(EventListener.class);
        listener = new Listener(mockListener);
    }

    @Test
    void testConstructorStoresEventListener() {
        assertEquals(mockListener, listener.getEventListener());
    }

    @Test
    void testSetSomeStoresValue() {
        String value = "test event";
        listener.setSome(value);

        Optional<?> result = listener.some();
        assertTrue(result.isPresent());
        assertEquals(value, result.get());
    }

    @Test
    void testSetSomeWithNullStoresEmptyOptional() {
        listener.setSome(null);

        Optional<?> result = listener.some();
        assertTrue(result.isEmpty());
    }

    @Test
    void testSomeInitiallyEmpty() {
        assertTrue(listener.some().isEmpty());
    }

    @Test
    void testToStringContainsFields() {
        listener.setSome("payload");
        String output = listener.toString();

        assertTrue(output.contains("eventListener"));
        assertTrue(output.contains("payload"));
    }
}
