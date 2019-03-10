package com.qub.Technopoly.io;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Closeable;
import java.io.IOException;
import java.util.Scanner;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
public class ScannerInput implements InputSource, Closeable {

    @NonNull
    private final Scanner scanner;

    @Getter
    private boolean isClosed;

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

    public String getNextString() {
        return scanner.next();
    }

    @Override
    public void close() throws IOException {
        scanner.close();
        isClosed = true;
    }
}
