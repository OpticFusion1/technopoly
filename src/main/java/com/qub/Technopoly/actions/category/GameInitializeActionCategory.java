package com.qub.Technopoly.actions.category;

import com.qub.Technopoly.Game;
import com.qub.Technopoly.actions.action.Action;
import com.qub.Technopoly.actions.action.ExitGameAction;
import com.qub.Technopoly.actions.action.LoadGameAction;
import com.qub.Technopoly.actions.action.StartNewGameAction;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.InputSource;
import com.qub.Technopoly.io.OutputSource;
import lombok.Getter;
import lombok.NonNull;

public class GameInitializeActionCategory implements ActionCategory {

    private static final String TITLE_DESCRIPTION = "Welcome to Technopoly!";
    private static final String INIT_DESCRIPTION =
        "To get started, select one of the actions below:";

    @Getter
    private final Action[] actions;

    private final OutputSource outputSource = IOHelper.getOutputSource();
    private final InputSource inputSource = IOHelper.getInputSource();

    public GameInitializeActionCategory(Game game, Board board){
        actions = new Action[]{
            new StartNewGameAction(board),
            new LoadGameAction(),
            new ExitGameAction(game)
        };
    }

    @Override
    public void describe() {
        outputSource.writeTitle(TITLE_DESCRIPTION);
        outputSource.writeBody(INIT_DESCRIPTION);

        describeActions(outputSource);
    }

    @Override
    public boolean execute() {
        var selected = inputSource.getNextInt();
        if (selected < 0 || selected >= actions.length) {
            outputSource.writeBody("Invalid action! Please select a valid action from the list");
            describeActions(outputSource);
            return false;
        }

        return actions[selected].execute();
    }
}
