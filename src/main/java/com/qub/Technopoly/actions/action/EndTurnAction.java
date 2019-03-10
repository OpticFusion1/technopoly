package com.qub.Technopoly.actions.action;

import lombok.NonNull;

public class EndTurnAction implements Action {

    private static final String NAME = "End Turn";
    private static final String DESCRIPTION = "End your turn";

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
