package com.qub.Technopoly.actions.category;

import com.qub.Technopoly.actions.action.Action;
import com.qub.Technopoly.actions.action.RollDiceAction;
import com.qub.Technopoly.actions.action.ViewPropertiesAction;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.config.InventoryConfig;
import com.qub.Technopoly.dice.DeveloperDice;
import com.qub.Technopoly.dice.Dice;
import com.qub.Technopoly.dice.RandomDice;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import com.qub.Technopoly.tile.Property;
import lombok.NonNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class NewTurnActionCategory implements ActionCategory {

    private static final String TITLE_DESCRIPTION_FORMAT = "It is your turn %s!";
    private static final String TITLE_BODY_FORMAT = "You currently have %s properties and %s %s.";

    @NonNull
    private final Actor actor;
    @NonNull
    private final Board board;

    private final InventoryConfig inventoryConfig = Config.getConfig().getInventoryConfig();
    private final OutputSource outputSource = IOHelper.getOutputSource();

    private final Action[] actions;

    public NewTurnActionCategory(Actor actor, Board board) {

        requireNonNull(actor);
        requireNonNull(board);

        this.actor = actor;
        this.board = board;
        actions = new Action[] {new RollDiceAction(getDiceForActor(actor)),
                                new ViewPropertiesAction(actor)};
    }

    @Override
    public void describe() {
        outputSource.writeTitle(format(TITLE_DESCRIPTION_FORMAT, actor.getActorName()));

        IOHelper.WaitSeconds(1f);

        var inventory = actor.getInventory();
        var currencySign = Math.abs(inventory.getCurrentBalance()) > 1 ?
            inventoryConfig.getBalanceCurrencyPlural() :
            inventoryConfig.getBalanceCurrencySingular();

        outputSource.writeBody(
            format(TITLE_BODY_FORMAT, actor.getInventory().getCountInInventory(Property.class),
                inventory.getCurrentBalance(), currencySign));

        IOHelper.WaitSeconds(1f);

        describeActions();
    }

    @Override
    public Action[] getActions() {
        return actions;
    }

    @Override
    public boolean execute() {
        var selected = getSelectedAction();
        if (selected < 0 || selected >= getActions().length) {
            IOHelper.getOutputSource()
                .writeBody("Invalid action! Please select a valid action from the list");
            describeActions();
            return false;
        }

        var action = getActions()[selected];
        var success = action.execute();
        if (!success) {
            describeActions();
        }

        if (action instanceof RollDiceAction) {
            var rollDice = (RollDiceAction) action;
            var roll = rollDice.getRoll();
            board.moveActor(actor, roll);
        }

        return success;
    }

    private Dice getDiceForActor(Actor actor) {
        var actorNameToLower = actor.getActorName().toLowerCase();
        var isDeveloper = actorNameToLower.contains("developer");
        var diceConfig = Config.getConfig().getDiceConfig();

        return isDeveloper ?
            new DeveloperDice(diceConfig, IOHelper.getInputSource()) :
            new RandomDice(IOHelper.getRandom(), diceConfig);
    }
}
