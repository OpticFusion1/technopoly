package com.qub.Technopoly.actions.group;

import com.qub.Technopoly.actions.action.Action;
import com.qub.Technopoly.actions.action.BackAction;
import com.qub.Technopoly.actions.action.UpgradePropertyAction;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.tile.Property;
import com.qub.Technopoly.util.Field;
import lombok.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.qub.Technopoly.io.IOHelper.getOutputSource;
import static java.util.Objects.requireNonNull;

public class ManagePropertiesActionGroup implements ActionGroup {

    @NonNull
    private final Actor actor;

    private final Action[] actions;

    public ManagePropertiesActionGroup(Actor actor) {
        requireNonNull(actor);
        this.actor = actor;

        List<Action> allActions =
            Arrays.stream(actor.getInventory().getTypeInInventory(Property.class))
                .filter(p -> Field.hasAllPropertiesInFieldForProperty(actor, p))
                .map(p -> new UpgradePropertyAction(actor, p)).collect(Collectors.toList());
        allActions.add(new BackAction());
        actions = allActions.toArray(Action[]::new);
    }

    @Override
    public void describe() {
        if(actions.length == 1){
            getOutputSource().writeBody("You have no completed fields.");
        }
        describeActions();
    }

    @Override
    public Action[] getActions() {
        return actions;
    }
}
