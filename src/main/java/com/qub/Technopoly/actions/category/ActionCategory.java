package com.qub.Technopoly.actions.category;

import com.qub.Technopoly.actions.action.Action;
import com.qub.Technopoly.io.OutputSource;
import org.apache.commons.lang3.ArrayUtils;

import static java.util.Objects.nonNull;

public interface ActionCategory {
    /**
     * Describes the action category
     *
     * @return
     */
    void describe();

    /**
     * Execute the action category.
     *
     * @return True if the action was executed, false if it wasn't.
     */
    boolean execute();

    /**
     * Get the actions that can be performed. The index corresponds to the expected input. (E.g. 1. Start New Game)
     *
     * @return
     */
    Action[] getActions();

    default void describeActions(OutputSource outputSource) {
        var actions = getActions();
        if (ArrayUtils.isEmpty(actions)) {
            return;
        }

        for (var i = 0; i < actions.length; i++) {
            var action = actions[i];
            var body = nonNull(action.getDescription()) ?
                action.getName() + " - " + action.getDescription() :
                action.getName();
            outputSource.writeBody(i + ".\t" + body);
        }
    }
}
