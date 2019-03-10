package com.qub.Technopoly.io;

import org.apache.commons.lang3.StringUtils;

public class PrettySystemOutput implements OutputSource {

    private static final char OUTER_BORDER = '*';
    private static final char INNER_BORDER = ' ';
    private static final int OUTER_BORDER_THICKNESS = 1;
    private static final int OUTER_PADDING = 3;
    private static final int INNER_PADDING = 1;

    @Override
    public void writeTitle(String title) {
        var border = StringUtils
            .repeat(OUTER_BORDER, title.length() + (OUTER_PADDING * 2) + (INNER_PADDING * 2));
        var outerPadding = StringUtils.repeat(OUTER_BORDER, OUTER_PADDING);
        var innerPadding = StringUtils.repeat(INNER_BORDER, INNER_PADDING);

        for (var i = 0; i < OUTER_BORDER_THICKNESS; i++) {
            System.out.println(border);
        }
        System.out.println(outerPadding + innerPadding + title + innerPadding + outerPadding);
        for (var i = 0; i < OUTER_BORDER_THICKNESS; i++) {
            System.out.println(border);
        }
    }

    @Override
    public void writeBody(String body) {
        System.out.println(body);
    }
}
