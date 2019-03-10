package com.qub.Technopoly.action;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import com.qub.Technopoly.tile.Property;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

@RequiredArgsConstructor
public class UnownedPropertyAction implements Action {

    private static final String DESCRIPTION_FORMAT =
        "Welcome to %s!\n%s\nYou can buy this property for %s.";

    private final OutputSource outputSource = IOHelper.getOutputSource();

    @NonNull
    private final Actor actor;
    @NonNull
    private final Property property;

    @Override
    public void describe() {
        outputSource.writeTitle(
            format(DESCRIPTION_FORMAT,
                property.getName(),
                property.getDescription(),
                property.getPrice()));
    }

    @Override
    public boolean execute() {
        return false;
    }
}
