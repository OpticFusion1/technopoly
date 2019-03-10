package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.dice.Dice;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

@RequiredArgsConstructor
public class RollDiceAction implements Action {

    private static final String EXECUTE_MESSAGE_FORMAT = "You rolled %s!";

    private static final String NAME = "Roll Dice";
    private static final String DESCRIPTION = "Roll the dice and see what you get!";

    private final OutputSource outputSource = IOHelper.getOutputSource();

    @NonNull
    private Dice dice;

    @Getter
    private int roll;

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
        roll = dice.roll();
        outputSource.writeBody(format(EXECUTE_MESSAGE_FORMAT, roll));

        IOHelper.WaitSeconds(1f);

        return true;
    }
}
