package com.qub.Technopoly.io;

/**
 * Prints Default system output with no specific formatting
 */
public class DefaultSystemOutput implements OutputSource {
    /**
     * {@inheritDoc}
     */
    @Override
    public void writeTitle(String title) {
        System.out.println(title);
        System.out.println();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeBody(String body) {
        System.out.println(body);
    }
}
