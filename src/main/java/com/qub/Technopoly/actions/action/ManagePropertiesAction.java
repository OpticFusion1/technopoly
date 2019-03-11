package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.actions.group.ManagePropertiesActionGroup;
import com.qub.Technopoly.actor.Actor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * {@inheritDoc}
 * Used to manage properties for an actor. Wraps the {@link ManagePropertiesActionGroup}
 */
@RequiredArgsConstructor
public class ManagePropertiesAction implements Action {

    private static final String NAME = "Manage Properties";
    private static final String DESCRIPTION = "Manage your owned properties";

    private ManagePropertiesActionGroup managePropertiesActionCategory;

    public ManagePropertiesAction(Actor actor) {
        managePropertiesActionCategory = new ManagePropertiesActionGroup(actor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NonNull String getName() {
        return NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute() {
        managePropertiesActionCategory.describe();
        return managePropertiesActionCategory.execute();
    }
}
