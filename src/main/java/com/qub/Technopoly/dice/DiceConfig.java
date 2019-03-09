package com.qub.Technopoly.dice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DiceConfig {

    private static final int MIN_ROLL = 1;
    private static final int MAX_ROLL = 6;

    @Getter
    private static final DiceConfig DEFAULT = new DiceConfig(MIN_ROLL, MAX_ROLL);

    private final int minRoll;

    private final int maxRoll;

    /**
     * Get the maximum number that can be rolled by an instance of {@link Dice}
     *
     * @return
     */
    public int getMaxRoll() {
        return maxRoll;
    }

    /**
     * Get the minimum number that can be rolled by an instance of {@link Dice}
     *
     * @return
     */
    public int getMinRoll() {
        return minRoll;
    }
}
