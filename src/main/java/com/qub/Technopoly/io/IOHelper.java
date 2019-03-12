package com.qub.Technopoly.io;

import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.config.DelayConfig;

import java.util.Random;
import java.util.Scanner;

import static java.lang.Float.compare;

public class IOHelper {

    private static ScannerInput inputSource;
    private static OutputSource outputSource = new PrettySystemOutput();

    // TODO - Remove seed later
    private static Random random = new Random(1234);

    private IOHelper() {
        // Prevent instantiation
    }

    /**
     * Helper method to return the current set {@link InputSource}
     * @return
     */
    public static InputSource getInputSource() {
        if (inputSource == null || inputSource.isClosed()) {
            inputSource = new ScannerInput(new Scanner(System.in));
        }
        return inputSource;
    }

    /**
     * Helper method to return the current set {@link OutputSource}
     * @return
     */
    public static OutputSource getOutputSource() {
        return outputSource;
    }

    /**
     * Gets the {@link Random} instance to use for the game
     * @return
     */
    public static Random getRandom() {
        return random;
    }

    /**
     * Forces a delay in execution based on seconds provided
     * @param seconds Seconds to wait
     */
    public static void waitSeconds(float seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Forces a delay in execution based on {@link DelayConfig}
     */
    public static void doActionDelay() {
        var waitSeconds = Config.getConfig().getDelayConfig().getActionWait();
        if (compare(waitSeconds, 0) == 0) {
            return;
        }
        waitSeconds(waitSeconds);
    }
}
