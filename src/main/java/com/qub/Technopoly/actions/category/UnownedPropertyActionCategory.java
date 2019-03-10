package com.qub.Technopoly.actions.category;

import com.qub.Technopoly.actions.action.Action;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import com.qub.Technopoly.tile.Property;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

@RequiredArgsConstructor
public class UnownedPropertyActionCategory implements ActionCategory {

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

    @Override
    public Action[] getActions() {
        // TODO - Implement
        return null;
    }
}
