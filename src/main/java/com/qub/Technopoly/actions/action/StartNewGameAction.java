package com.qub.Technopoly.actions.action;

import lombok.NonNull;

public class StartNewGameAction implements Action {

    private static final String NAME = "Start New Game";
    private static final String DESCRIPTION = null;

    @Override
    public @NonNull String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public void execute() {

    }
}
