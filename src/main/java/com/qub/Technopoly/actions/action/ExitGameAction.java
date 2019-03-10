package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.Game;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExitGameAction implements Action {

    private static final String EXECUTE_MESSAGE = "Thanks for playing Technopoly! See you later.";

    private static final String NAME = "Exit Game";
    private static final String DESCRIPTION = null;

    private final OutputSource outputSource = IOHelper.getOutputSource();

    @NonNull
    private final Game game;

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
        outputSource.writeTitle(EXECUTE_MESSAGE);
        if (game.isGameRunning()) {
            game.stop();
        }
        return true;
    }
}
