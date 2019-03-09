package com.qub.Technopoly.input;


   import java.io.Closeable;
   import java.io.IOException;
   import java.util.Scanner;

import static java.util.Objects.isNull;

public class ScannerInput implements InputSource, Closeable {

    private static ScannerInput instance;

    private final Scanner scanner;

    private ScannerInput() {
        scanner = new Scanner(System.in);
    }

    public int getNextInt() {
        Integer next = null;
        do {
            if(!scanner.hasNext()){
                continue;
            }
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number");
                scanner.next();
                continue;
            }
            next = scanner.nextInt();
        }
        while (isNull(next));

        return next;
    }

    public String getNextString() {
        return scanner.next();
    }

    public static ScannerInput getInstance() {
        if (instance == null) {
            instance = new ScannerInput();
        }
        return instance;
    }

    @Override
    public void close() throws IOException {
        scanner.close();
        instance = null;
    }
}
