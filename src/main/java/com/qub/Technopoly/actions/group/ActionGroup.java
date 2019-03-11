package com.qub.Technopoly.actions.group;

import com.qub.Technopoly.actions.action.Action;
import com.qub.Technopoly.io.IOHelper;
import org.apache.commons.lang3.ArrayUtils;

import static java.util.Objects.nonNull;

/**
 * An action group wraps {@link Action} and provides a convenient way to group them/call them.
 */
public interface ActionGroup {
    /**
     * Describes the action group
     *
     * @return
     */
    void describe();

    /**
     * Execute the action group.
     *
     * @return True if the action was executed, false if it wasn't.
     */
    default boolean execute() {
        var selected = getSelectedAction();
        if (selected < 0 || selected >= getActions().length) {
            IOHelper.getOutputSource()
                .writeBody("Invalid action! Please select a valid action from the list");
            describeActions();
            return false;
        }

        var success = getActions()[selected].execute();
        if (!success) {
            describeActions();
        }
        return success;
    }

    /**
     * Get the actions that can be performed. The index corresponds to the expected input. (E.g. 1. Start New Game)
     *
     * @return
     */
    Action[] getActions();

    /**
     * Describes the {@link Action}s that this group contains
     */
    default void describeActions() {
        var actions = getActions();
        if (ArrayUtils.isEmpty(actions)) {
            return;
        }

        for (var i = 0; i < actions.length; i++) {
            var action = actions[i];
            var body = nonNull(action.getDescription()) ?
                action.getName() + " - " + action.getDescription() :
                action.getName();
            IOHelper.getOutputSource().writeBody((i + 1) + ".\t" + body);
        }
    }

    /**
     * Gets the next selected action by the user
     * @return
     */
    default int getSelectedAction() {
        return IOHelper.getInputSource().getNextInt() - 1;
    }
}
