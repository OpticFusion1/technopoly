package com.qub.Technopoly.action;

public interface Action {
    /**
     * Describes the action
     * @return
     */
    void describe();

    /**
     * Execute the action.
     * @return True if the action was executed, false if it wasn't.
     */
    boolean execute();
}
