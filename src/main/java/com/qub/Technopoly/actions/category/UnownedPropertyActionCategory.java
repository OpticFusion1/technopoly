package com.qub.Technopoly.actions.category;

import com.qub.Technopoly.actions.action.Action;
import com.qub.Technopoly.actions.action.AuctionPropertyAction;
import com.qub.Technopoly.actions.action.BuyPropertyAction;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import com.qub.Technopoly.tile.Property;
import lombok.NonNull;

import static com.qub.Technopoly.io.IOHelper.DoActionDelay;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class UnownedPropertyActionCategory implements ActionCategory {

    private static final String DESCRIPTION_FORMAT = "Welcome to %s!";
    private static final String DESCRIPTION_TWO_FORMAT = "You can buy this property for %s %s.";

    private final OutputSource outputSource = IOHelper.getOutputSource();

    @NonNull
    private final Actor actor;
    @NonNull
    private final Property property;

    private final Action[] actions;

    public UnownedPropertyActionCategory(Actor actor, Property property) {
        requireNonNull(actor);
        requireNonNull(property);

        this.actor = actor;
        this.property = property;

        actions =
            new Action[] {new BuyPropertyAction(actor, property), new AuctionPropertyAction()};
    }

    @Override
    public void describe() {
        outputSource.writeTitle(format(DESCRIPTION_FORMAT, property.getName()));
        outputSource.writeBody(property.getDescription());

        DoActionDelay();

        outputSource.writeBody(format(DESCRIPTION_TWO_FORMAT, property.getPrice(),
            Config.getConfig().getInventoryConfig().getCurrencyName()));

        describeActions();
    }

    @Override
    public Action[] getActions() {
        return actions;
    }
}
