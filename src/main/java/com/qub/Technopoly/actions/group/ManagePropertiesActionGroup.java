package com.qub.Technopoly.actions.group;

import com.qub.Technopoly.actions.action.Action;
import com.qub.Technopoly.actions.action.BackAction;
import com.qub.Technopoly.actions.action.UpgradePropertyAction;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.io.IOHelper;
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

    private final Actor actor;
    private Action[] actions;

    public ManagePropertiesActionGroup(Actor actor) {
        requireNonNull(actor);
        this.actor = actor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void describe() {
        if (getActions().length == 1) {
            getOutputSource().writeBody("You have no completed fields.");
        }
        describeActions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action[] getActions() {
        List<Action> allActions =
            Arrays.stream(actor.getInventory().getTypeInInventory(Property.class))
                .filter(p -> Field.hasAllPropertiesInFieldForProperty(actor, p))
                .map(p -> new UpgradePropertyAction(actor, p, getOutputSource()))
                .collect(Collectors.toList());
        allActions.add(new BackAction());
        actions = allActions.toArray(Action[]::new);
        return actions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute() {
        boolean backSelected = false;
        while(!backSelected) {
            var selected = getSelectedAction();
            if (selected < 0 || selected >= getActions().length) {
                IOHelper.getOutputSource().writeBody("Invalid action! Please select a valid action from the list");
                describeActions();
                return false;
            }

            var selectedAction = getActions()[selected];
            selectedAction.execute();
            if(selectedAction instanceof BackAction){
                backSelected = true;
                return false;
            }

            describeActions();
        }
        return false;
    }
}
