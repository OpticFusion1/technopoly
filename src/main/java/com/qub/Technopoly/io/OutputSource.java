package com.qub.Technopoly.io;

public interface OutputSource {

    /**
     * Writes a title statement for the user
     * @param title The text the title should contain
     */
    void writeTitle(String title);

    /**
     * Writes a body statement for the user
     * @param body The text the body should contain
     */
    void writeBody(String body);
}
