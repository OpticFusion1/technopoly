package com.qub.Technopoly.io;

public class DefaultSystemOutput implements OutputSource {
    @Override
    public void writeTitle(String title) {
        System.out.println(title);
        System.out.println();
    }

    @Override
    public void writeBody(String body) {
        System.out.println(body);
    }
}
