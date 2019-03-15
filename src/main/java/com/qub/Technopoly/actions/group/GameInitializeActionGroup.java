package com.qub.Technopoly.actions.group;

import com.qub.Technopoly.Game;
import com.qub.Technopoly.actions.action.Action;
import com.qub.Technopoly.actions.action.ExitGameAction;
import com.qub.Technopoly.actions.action.LoadGameAction;
import com.qub.Technopoly.actions.action.StartNewGameAction;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.io.OutputSource;
import lombok.Getter;

import static com.qub.Technopoly.io.IOHelper.getInputSource;
import static com.qub.Technopoly.io.IOHelper.getOutputSource;

/**
 * {@inheritDoc}
 * Used to initialize the game
 */
public class GameInitializeActionGroup implements ActionGroup {

    private static final String TITLE_DESCRIPTION = "Welcome to Technopoly!";
    private static final String INIT_DESCRIPTION =
        "To get started, select one of the actions below:";

    @Getter
    private final Action[] actions;

    private final OutputSource outputSource = getOutputSource();

    public GameInitializeActionGroup(Game game, Board board) {
        actions = new Action[] {new StartNewGameAction(game, board, outputSource, getInputSource()),
                                new ExitGameAction(game)};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void describe() {
        outputSource.writeTitle(TITLE_DESCRIPTION);
        outputSource.writeBody(INIT_DESCRIPTION);

        describeActions();
    }
}
