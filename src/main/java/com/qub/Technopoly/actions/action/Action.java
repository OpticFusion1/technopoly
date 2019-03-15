package com.qub.Technopoly.actions.action;

import lombok.NonNull;

/**
 * Represents a specific action that can be executed
 */
public interface Action {
    /**
     * Gets the action name
     * @return The action name
     */
    @NonNull
    String getName();

    /**
     * Get the actions description
     * @return The action description
     */
    String getDescription();

    /**
     * Executes the action
     * @return Whether the action succeeded or not
     */
    boolean execute();
}
