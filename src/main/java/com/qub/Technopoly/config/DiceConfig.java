package com.qub.Technopoly.config;

import com.qub.Technopoly.dice.Dice;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DiceConfig {

    private static final int MIN_ROLL = 1;
    private static final int MAX_ROLL = 6;

    @Getter
    private static final DiceConfig DEFAULT = new DiceConfig(MIN_ROLL, MAX_ROLL);

    @Getter
    private final int minRoll;

    @Getter
    private final int maxRoll;
}
