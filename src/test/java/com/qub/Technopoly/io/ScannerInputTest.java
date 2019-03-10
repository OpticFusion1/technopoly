package com.qub.Technopoly.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScannerInputTest {

    private ScannerInput scannerInput;

    @AfterEach
    public void teardown() throws IOException {
        scannerInput.close();
    }

    @Test
    public void GetNextIntReturnsExpectedInteger() {
        var expected = 93;
        setInput(expected);
        assertEquals(expected, getScannerInputLazy().getNextInt());
    }

    @Test
    public void GetNextStringReturnsExpectedString() {
        var expected = 93;
        setInput(expected);
        assertEquals(valueOf(expected), getScannerInputLazy().getNextString());
    }

    private void setInput(int input) {
        var in = new ByteArrayInputStream(valueOf(input).getBytes());
        System.setIn(in);
    }

    private ScannerInput getScannerInputLazy() {
        if (scannerInput == null) {
            scannerInput = new ScannerInput(new Scanner(System.in));
        }
        return scannerInput;
    }
}
