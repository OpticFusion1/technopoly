package com.qub.Technopoly.actions.action;

import lombok.NonNull;

public class BackAction implements Action {

    private static final String NAME = "Back";
    private static final String DESCRIPTION = "Go back to main menu";

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
        return true;
    }
}
