package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.dice.Dice;
import com.qub.Technopoly.exception.GameStateException;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import lombok.Getter;
import lombok.NonNull;

import static com.qub.Technopoly.io.IOHelper.doActionDelay;
import static java.lang.String.format;

/**
 * {@inheritDoc}
 * Used to roll the dice
 */
public class RollDiceAction implements Action {

    private static final String EXECUTE_MESSAGE_FORMAT = "You rolled %s and %s for a total of %s!";
    private static final String EXECUTE_DOUBLES_MESSAGE = "DOUBLES! You get one more turn!";

    private static final String NAME = "Roll Dice";
    private static final String DESCRIPTION = "Roll the dice and see what you get!";

    private final OutputSource outputSource = IOHelper.getOutputSource();

    @NonNull
    private final Dice[] dice;

    @Getter
    private int roll;

    @Getter
    private boolean rolledDoubles;

    public RollDiceAction(Dice[] dice) {
        if (dice.length != Config.getConfig().getDiceConfig().getAmountDice()) {
            throw new GameStateException("Amount of dice is not equal to expected amount!");
        }
        this.dice = dice;
    }

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

        rolledDoubles = false;

        // TODO - Current dice count is hardcoded to 2.
        // TODO - Change this in future if you want to have more dice.
        var firstDiceRoll = dice[0].roll();
        var secondDiceRoll = dice[1].roll();

        roll = firstDiceRoll + secondDiceRoll;
        outputSource.writeBody(format(EXECUTE_MESSAGE_FORMAT, firstDiceRoll, secondDiceRoll, roll));

        if (firstDiceRoll == secondDiceRoll) {
            outputSource.writeBody(EXECUTE_DOUBLES_MESSAGE);
            rolledDoubles = true;
        }

        doActionDelay();

        return true;
    }
}
