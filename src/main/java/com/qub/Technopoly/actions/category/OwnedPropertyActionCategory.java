package com.qub.Technopoly.actions.category;

import com.qub.Technopoly.actions.action.Action;
import com.qub.Technopoly.actions.action.EndTurnAction;
import com.qub.Technopoly.actions.action.PayRentAction;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import com.qub.Technopoly.tile.Property;
import com.qub.Technopoly.util.Field;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.qub.Technopoly.io.IOHelper.DoActionDelay;
import static java.lang.String.format;

@RequiredArgsConstructor
public class OwnedPropertyActionCategory implements ActionCategory {

    private static final String DESCRIPTION_FIELD_FORMAT = "Welcome to %s!";
    private static final String DESCRIPTION_PROPERTY_FORMAT = "You are currently in %s";

    private static final String OWNED_SELF_DESCRIPTION = "This property is owned by you.";
    private static final String OWNED_OTHER_DESCRIPTION_FORMAT =
        "This property is owned by %s. Rent is %s %s";

    private final OutputSource outputSource = IOHelper.getOutputSource();
    @NonNull
    private final Actor actor;
    @NonNull
    private final Property property;

    @Override
    public void describe() {
        var field = Field.getFieldForProperty(property);
        outputSource.writeTitle(format(DESCRIPTION_FIELD_FORMAT, field.getName()));
        outputSource.writeBody(field.getDescription());

        DoActionDelay();

        outputSource.writeBody(format(DESCRIPTION_PROPERTY_FORMAT, property.getName()));
        outputSource.writeBody(property.getDescription());

        DoActionDelay();

        var description = property.getOwner().equals(actor) ?
            OWNED_SELF_DESCRIPTION :
            format(OWNED_OTHER_DESCRIPTION_FORMAT, property.getOwner().getActorName(),
                property.getRent(), Config.getConfig().getInventoryConfig().getCurrencyName());
        outputSource.writeTitle(description);

        describeActions();
    }

    @Override
    public Action[] getActions() {

        if (property.getOwner().equals(actor)) {
            // Return Owned Actions
            return new Action[] {new EndTurnAction()};
        } else {
            // Return Other's Actions
            return new Action[] {new PayRentAction(actor, property)};
        }
    }
}
