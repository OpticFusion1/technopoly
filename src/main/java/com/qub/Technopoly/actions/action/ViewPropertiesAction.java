package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import com.qub.Technopoly.tile.Property;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

@RequiredArgsConstructor
public class ViewPropertiesAction implements Action {

    private static final String EXECUTE_MESSAGE_FORMAT = "Property %s";

    private static final String NAME = "View Properties";
    private static final String DESCRIPTION = "View your owned properties";

    private final OutputSource outputSource = IOHelper.getOutputSource();

    @NonNull
    private final Actor actor;

    @Override
    public @NonNull String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public boolean execute() {
        var properties = actor.getInventory().getTypeInInventory(Property.class);
        for (var property : properties) {
            outputSource.writeBody(format(EXECUTE_MESSAGE_FORMAT, property.getName()));
        }
        return true;
    }
}
