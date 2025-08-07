package com.aey.mox.core;

/**
 * The {@code Actions} enum defines a set of predefined event action names
 * used within the event-driven system. Each enum constant wraps a string
 * that represents the action identifier used in event subscriptions and emissions.
 */
public enum Actions {
    PUBLISH("publish")
    ;

    private final String action;

    /**
     * Constructs an {@code Actions} enum with the specified action string.
     *
     * @param action The string identifier for the action
     */
    Actions(String action) {
        this.action = action;
    }

    /**
     * Returns the string representation of the action.
     *
     * @return The action identifier as a string
     */
    public String getAction() {
        return this.action;
    }
}
