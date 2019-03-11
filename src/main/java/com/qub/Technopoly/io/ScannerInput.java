package com.qub.Technopoly.io;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Closeable;
import java.io.IOException;
import java.util.Scanner;

import static java.util.Objects.isNull;

/**
 * Wraps {@link Scanner} as a convenient way of handling errors while hiding complexity for usage in the game.
 * Additionally, allows mocking input without having problems with IO
 */
@RequiredArgsConstructor
public class ScannerInput implements InputSource, Closeable {

    @NonNull
    private final Scanner scanner;

    @Getter
    private boolean isClosed;

    /**
     * {@inheritDoc}
     */
    public int getNextInt() {
        Integer next = null;
        do {
            if (!scanner.hasNext()) {
                continue;
            }
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number");
                scanner.next();
                continue;
            }
            next = scanner.nextInt();
        } while (isNull(next));

        return next;
    }

    /**
     * {@inheritDoc}
     */
    public String getNextString() {
        return scanner.next();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws IOException {
        scanner.close();
        isClosed = true;
    }
}
