package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import com.qub.Technopoly.tile.Property;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

/**
 * {@inheritDoc}
 * Used to pay rent to a specific actor, from a specific actor
 */
@RequiredArgsConstructor
public class PayRentAction implements Action {

    private static final String EXECUTE_CAN_AFFORD_MESSAGE_FORMAT =
        "You paid %s %s to %s.\nYour new balance is %s %s.";
    private static final String EXECUTE_CANT_AFFORD_MESSAGE_FORMAT =
        "You can't afford rent! You are declared BANKRUPT.\nYour remaining balance has been transferred to %s";
    private static final String NAME = "Pay Rent";
    private static final String DESCRIPTION_FORMAT = "Pay rent of %s %s to %s.";

    @NonNull
    private final Actor sender;
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
        var currency = Config.getConfig().getInventoryConfig().getCurrencyName();
        return format(DESCRIPTION_FORMAT, property.getRent(), currency,
            property.getOwner().getActorName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute() {
        var senderInventory = sender.getInventory();
        var receiverInventory = property.getOwner().getInventory();
        var canAfford = senderInventory.getCurrentBalance() >= property.getRent();

        var currency = Config.getConfig().getInventoryConfig().getCurrencyName();

        if (canAfford) {
            senderInventory.remove(property.getRent());
            receiverInventory.add(property.getRent());

            outputSource.writeBody(
                format(EXECUTE_CAN_AFFORD_MESSAGE_FORMAT, property.getRent(), currency,
                    property.getOwner().getActorName(), senderInventory.getCurrentBalance(),
                    currency));
        } else {
            receiverInventory.add(senderInventory.getCurrentBalance());
            senderInventory.remove(property.getRent());

            outputSource.writeBody(
                format(EXECUTE_CANT_AFFORD_MESSAGE_FORMAT, property.getOwner().getActorName()));
        }

        return true;
    }
}
