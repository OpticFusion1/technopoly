package com.qub.Technopoly.dice;

import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.config.DiceConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomDiceTest {

    private static final long RANDOM_SEED = 1234567;
    private final DiceConfig diceConfig = Config.getConfig().getDiceConfig();
    private Random seededRandom;
    private Dice dice;


    @BeforeEach
    void setUp() throws Exception {
        seededRandom = new Random(RANDOM_SEED);
        dice = new RandomDice(seededRandom, diceConfig);
    }

    @RepeatedTest(100)
    void rollReturnsValuesGreaterThanOrEqualToMinRoll() {
        var roll = dice.roll();
        assertTrue(roll >= diceConfig.getMinRoll());
    }

    @RepeatedTest(100)
    void rollReturnsValuesGreaterThanOrEqualToMaxRoll() {
        var roll = dice.roll();
        assertTrue(diceConfig.getMaxRoll() >= roll);
    }
}
