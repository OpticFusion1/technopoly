package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.actions.group.ManagePropertiesActionGroup;
import com.qub.Technopoly.actor.Actor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ManagePropertiesAction implements Action {

    private static final String NAME = "Manage Properties";
    private static final String DESCRIPTION = "Manage your owned properties";

    private ManagePropertiesActionGroup managePropertiesActionCategory;

    public ManagePropertiesAction(Actor actor) {
        managePropertiesActionCategory = new ManagePropertiesActionGroup(actor);
    }

    @Override
    public @NonNull String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public boolean execute() {
        managePropertiesActionCategory.describe();
        return managePropertiesActionCategory.execute();
    }
}
