package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import lombok.NonNull;

/**
 * {@inheritDoc}
 * Loads the most recently saved game from disk
 */
public class LoadGameAction implements Action {

    private static final String NAME = "Load Game";
    private static final String DESCRIPTION = null;

    private final OutputSource outputStream = IOHelper.getOutputSource();

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
        outputStream.writeBody("Sorry, Load Game is not implemented yet");
        return false;
    }
}
