package com.qub.Technopoly.io;

import com.qub.Technopoly.config.Config;

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

    public static InputSource getInputSource() {
        if (inputSource == null || inputSource.isClosed()) {
            inputSource = new ScannerInput(new Scanner(System.in));
        }
        return inputSource;
    }

    public static OutputSource getOutputSource() {
        return outputSource;
    }

    public static Random getRandom() {
        return random;
    }

    public static void WaitSeconds(float seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void DoActionDelay() {
        var waitSeconds = Config.getConfig().getDelayConfig().getActionWait();
        if (compare(waitSeconds, 0) == 0) {
            return;
        }
        WaitSeconds(waitSeconds);
    }
}
