package com.qub.Technopoly.action;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import com.qub.Technopoly.tile.Property;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

@RequiredArgsConstructor
public class OwnedPropertyAction implements Action {

    private static final String OWNED_SELF_DESCRIPTION_FORMAT =
        "Welcome to %s!\nThis property is owned by you.";
    private static final String OWNED_OTHER_DESCRIPTION_FORMAT =
        "Welcome to %s!\nThis property is owned by %s. Rent is %s.";

    private final OutputSource outputSource = IOHelper.getOutputSource();
    @NonNull
    private final Actor actor;
    @NonNull
    private final Property property;

    @Override
    public void describe() {
        var description = property.getOwner().equals(actor) ?
            format(OWNED_SELF_DESCRIPTION_FORMAT, property.getName()) :
            format(OWNED_OTHER_DESCRIPTION_FORMAT, property.getName(),
                property.getOwner().getActorName(), property.getRent());

        outputSource.writeTitle(description);
    }

    @Override
    public boolean execute() {
        // TODO - Implement
        return false;
    }
}
