package com.qub.Technopoly.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.qub.Technopoly.io.IOHelper.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class IOHelperTest {
    @Test
    public void getInputSourceReturnsNotNullInputSource(){
        assertNotNull(getInputSource());
    }

    @Test
    public void getInputSourceReturnsNewInputSourceWhenUnderlyingInputSourceWasClosed()
        throws IOException {
        var firstInputSource = getInputSource();
        ((ScannerInput)firstInputSource).close();
        var nextInputSource = getInputSource();

        assertNotSame(firstInputSource, nextInputSource);
    }

    @Test
    public void getOutputSourceReturnsNotNullOutputSource(){
        assertNotNull(getOutputSource());
    }

    @Test
    public void getRandomReturnsNotNullRandom(){
        assertNotNull(getRandom());
    }
}
