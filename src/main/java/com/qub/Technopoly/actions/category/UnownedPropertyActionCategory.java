package com.qub.Technopoly.actions.category;

import com.qub.Technopoly.actions.action.Action;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import com.qub.Technopoly.tile.Property;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.qub.Technopoly.io.IOHelper.WaitSeconds;
import static java.lang.String.format;

@RequiredArgsConstructor
public class UnownedPropertyActionCategory implements ActionCategory {

    private static final String DESCRIPTION_FORMAT = "Welcome to %s!";
    private static final String DESCRIPTION_TWO_FORMAT = "You can buy this property for %s %s.";

    private final OutputSource outputSource = IOHelper.getOutputSource();

    @NonNull
    private final Actor actor;
    @NonNull
    private final Property property;

    @Override
    public void describe() {
        outputSource.writeTitle(format(DESCRIPTION_FORMAT, property.getName()));
        outputSource.writeBody(property.getDescription());

        WaitSeconds(1.0f);

        outputSource.writeBody(format(DESCRIPTION_TWO_FORMAT, property.getPrice(),
            Config.getConfig().getInventoryConfig().getBalanceCurrencyPlural()));
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
