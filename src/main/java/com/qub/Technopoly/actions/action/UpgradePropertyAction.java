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
 * Used to upgrade a specific property
 */
@RequiredArgsConstructor
public class UpgradePropertyAction implements Action {

    private static final String NAME = "Upgrade Property";
    private static final String DESCRIPTION_FORMAT = "Build %s for %s %s. Rent %s %s";
    private static final String MAX_UPGRADE_DESCRIPTION = "Property is already upgraded to maximum";
    private static final String NOT_ENOUGH_MONEY_MESSAGE = "Sorry, you can't afford this upgrade";
    private static final String BUY_UPGRADE_MESSAGE =
        "You built the %s!\nYour new balance is %s %s.";

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
        if (!property.canUpgrade()) {
            return MAX_UPGRADE_DESCRIPTION;
        }

        var nextHouse = property.getNextHouseInfo();
        var currency = Config.getConfig().getInventoryConfig().getCurrencyName();
        return format(DESCRIPTION_FORMAT, nextHouse.getHouseName(), nextHouse.getHousePrice(),
            currency, nextHouse.getHouseRent(), currency);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute() {
        if (!property.canUpgrade()) {
            outputSource.writeBody(MAX_UPGRADE_DESCRIPTION);
            return false;
        }

        var inventory = actor.getInventory();
        var nextHouse = property.getNextHouseInfo();
        if (nextHouse.getHousePrice() > inventory.getCurrentBalance()) {
            outputSource.writeBody(NOT_ENOUGH_MONEY_MESSAGE);
            return false;
        }

        inventory.remove(nextHouse.getHousePrice());
        property.addHouse();

        outputSource.writeBody(
            format(BUY_UPGRADE_MESSAGE, property.getName(), inventory.getCurrentBalance(),
                Config.getConfig().getInventoryConfig().getCurrencyName()));

        doActionDelay();

        return true;
    }
}
