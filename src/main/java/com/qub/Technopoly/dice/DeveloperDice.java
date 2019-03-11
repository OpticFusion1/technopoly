package com.qub.Technopoly.dice;

import com.qub.Technopoly.config.DiceConfig;
import com.qub.Technopoly.io.InputSource;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * The DeveloperDice is used for testing.
 * It allows the developer to specify the dice roll they would like to get.
 */
@RequiredArgsConstructor
public class DeveloperDice implements Dice {

    @NonNull
    private final DiceConfig diceConfig;

    @NonNull
    private final InputSource inputSource;

    /**
     * {@inheritDoc}
     */
    @Override
    public int roll() {

        var minRoll = diceConfig.getMinRoll();
        var maxRoll = diceConfig.getMaxRoll();

        var roll = 0;
        var hasRoll = false;
        do {
            System.out.printf("Select a Dice roll (%s-%s):", minRoll, maxRoll);

            roll = inputSource.getNextInt();

            if ((roll >= minRoll) && (roll <= maxRoll)) {
                hasRoll = true;
            } else {
                System.out.printf("Number must be between %s-%s\n", minRoll, maxRoll);
            }
        } while (!hasRoll);

        return roll;
    }
}
