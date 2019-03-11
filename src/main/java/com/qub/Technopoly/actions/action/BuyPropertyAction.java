package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import com.qub.Technopoly.tile.Property;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.qub.Technopoly.io.IOHelper.doActionDelay;
import static java.lang.String.format;

/**
 * {@inheritDoc}
 * Used to buy a property
 */
@RequiredArgsConstructor
public class BuyPropertyAction implements Action {

    private static final String NAME = "Buy Property";
    private static final String DESCRIPTION = "Become the new lucky owner of this property";
    private static final String NOT_ENOUGH_MONEY_MESSAGE = "Sorry, you can't afford this property";
    private static final String BUY_PROPERTY_MESSAGE =
        "You are the proud new owner of %s!\nYour new balance is %s %s.";

    @NonNull
    private final Actor actor;
    @NonNull
    private final Property property;

    private final OutputSource outputSource = IOHelper.getOutputSource();

    /**
     * {@inheritDoc}
     */
    @Override
    public @NonNull String getName() {
        return NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute() {
        var inventory = actor.getInventory();
        if (property.getPrice() > inventory.getCurrentBalance()) {
            outputSource.writeBody(NOT_ENOUGH_MONEY_MESSAGE);
            return false;
        }

        property.setOwner(actor);
        inventory.add(property);
        inventory.remove(property.getPrice());

        outputSource.writeBody(
            format(BUY_PROPERTY_MESSAGE, property.getName(), inventory.getCurrentBalance(),
                Config.getConfig().getInventoryConfig().getCurrencyName()));

        doActionDelay();

        return true;
    }
}
