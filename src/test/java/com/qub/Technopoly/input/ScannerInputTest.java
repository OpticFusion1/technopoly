package com.qub.Technopoly.input;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScannerInputTest {

    @AfterEach
    public void teardown() throws IOException {
        ScannerInput.getInstance().close();
    }

    @Test
    public void GetNextIntReturnsExpectedInteger() {
        var expected = 93;
        setInput(expected);
        assertEquals(expected, ScannerInput.getInstance().getNextInt());
    }

    @Test
    public void GetNextStringReturnsExpectedString() {
        var expected = 93;
        setInput(expected);
        assertEquals(valueOf(expected), ScannerInput.getInstance().getNextString());
    }

    private void setInput(int input) {
        var in = new ByteArrayInputStream(valueOf(input).getBytes());
        System.setIn(in);
    }
}
