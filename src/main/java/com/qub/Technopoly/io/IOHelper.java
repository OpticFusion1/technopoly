package com.qub.Technopoly.io;

import java.util.Scanner;

public class IOHelper {

    private static ScannerInput inputSource;
    private static OutputSource outputSource = new DefaultSystemOutput();

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
}
