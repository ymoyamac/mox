package com.aey.mox.publisher;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.aey.mox.listeners.EventListener;
import com.aey.mox.multy.EventManager;

public class EventManagerTest {
    private EventManager manager;
    private TestListener listener;

    static class TestListener implements EventListener {
        String lastEventType;
        Object lastPayload;

        @Override
        public <T> void update(String eventType, T payload) {
            this.lastEventType = eventType;
            this.lastPayload = payload;
        }
    }

    @BeforeEach
    void setUp() {
        manager = new EventManager("publish");
        listener = new TestListener();
    }

    @Test
    void testSubscribeAndEmit() {
        manager.subscribe("publish", listener);

        manager.emit("publish", "Hello");
        assertEquals("publish", listener.lastEventType);
        assertEquals("Hello", listener.lastPayload);
        assertTrue(manager.isSome("publish", listener));
    }

    @Test
    void testSubscribeAndNotify() {
        manager.subscribe("publish", listener);
        manager.notify("publish", listener, 123);
        assertEquals("publish", listener.lastEventType);
        assertEquals(123, listener.lastPayload);
        assertEquals(Optional.of(123), manager.ok("publish", listener));
    }

    @Test
    void testUnsubscribe() {
        manager.subscribe("publish", listener);
        manager.unsubscribe("publish", listener);
        manager.emit("publish", "Data");

        // Listener should NOT receive the event
        assertNull(listener.lastPayload);
        assertFalse(manager.isSome("publish", listener));
    }

    @Test
    void testRemove() {
        manager.subscribe("publish", listener);
        manager.unsubscribe("publish", listener);
        manager.unsubscribe("publish", listener);

        assertNull(listener.lastPayload);
    }

    @Test
    void testOkReturnsEmptyForUnsubscribed() {
        assertEquals(Optional.empty(), manager.ok("publish", listener));
    }

    @Test
    void testIsSomeReturnsFalseWhenListenerNotRegistered() {
        assertFalse(manager.isSome("publish", listener));
    }

    @Test
    void testSetActionAddsNewAction() {
        manager.setAction("custom");
        manager.subscribe("custom", listener);
        manager.emit("custom", "payload");
        assertEquals("payload", listener.lastPayload);
    }

    @Test
    void testActionsArrayIsCorrect() {
        assertArrayEquals(new String[]{"publish"}, manager.actions());
    }

    @Test
    void testToString() {
        String toString = manager.toString();
        assertTrue(toString.contains("EventManager {listeners="));
    }
}
