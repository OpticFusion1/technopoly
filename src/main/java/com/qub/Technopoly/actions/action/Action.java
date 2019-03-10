package com.qub.Technopoly.actions.action;

import lombok.NonNull;

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
     */
    boolean execute();
}
