package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.dice.Dice;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.qub.Technopoly.io.IOHelper.doActionDelay;
import static java.lang.String.format;

/**
 * {@inheritDoc}
 * Used to roll the dice
 */
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
        roll = dice.roll();
        outputSource.writeBody(format(EXECUTE_MESSAGE_FORMAT, roll));

        doActionDelay();

        return true;
    }
}
