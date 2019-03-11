package com.qub.Technopoly.actions.group;

import com.qub.Technopoly.actions.action.Action;
import com.qub.Technopoly.actions.action.BackAction;
import com.qub.Technopoly.actions.action.UpgradePropertyAction;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.tile.Property;
import com.qub.Technopoly.util.Field;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.qub.Technopoly.io.IOHelper.getOutputSource;
import static java.util.Objects.requireNonNull;

/**
 * {@inheritDoc}
 * Used to Manage a specific actor's properties
 */
public class ManagePropertiesActionGroup implements ActionGroup {

    private final Action[] actions;

    public ManagePropertiesActionGroup(Actor actor) {
        requireNonNull(actor);

        List<Action> allActions =
            Arrays.stream(actor.getInventory().getTypeInInventory(Property.class))
                .filter(p -> Field.hasAllPropertiesInFieldForProperty(actor, p))
                .map(p -> new UpgradePropertyAction(actor, p, getOutputSource()))
                .collect(Collectors.toList());
        allActions.add(new BackAction());
        actions = allActions.toArray(Action[]::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void describe() {
        if (actions.length == 1) {
            getOutputSource().writeBody("You have no completed fields.");
        }
        describeActions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action[] getActions() {
        return actions;
    }
}
