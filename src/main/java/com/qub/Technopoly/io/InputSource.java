package com.qub.Technopoly.io;

public interface InputSource {

    /**
     * Gets the next integer provided by the user
     * @return The next integer provided by the user
     */
    int getNextInt();

    /**
     * Gets the next string provided by the user
     * @return The next string provided by the user
     */
    String getNextString();
}
