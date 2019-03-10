package com.qub.Technopoly.dice;

import com.qub.Technopoly.config.DiceConfig;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Random;

/**
 * The RandomDice class uses an instance of {@link Random} to roll a number between
 * min value and max value specified by {@link DiceConfig}
 *
 * @author lakrs
 */
@RequiredArgsConstructor
public class RandomDice implements Dice {

    @NonNull
    private final Random random;
    @NonNull
    private final DiceConfig diceConfig;

    /**
     * {@inheritDoc}
     */
    @Override
    public int roll() {
        return random.nextInt(diceConfig.getMaxRoll()) + diceConfig.getMinRoll();
    }

}
