package com.qub.Technopoly.tile;

import com.qub.Technopoly.actions.category.ActionCategory;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.config.StartConfig;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

@RequiredArgsConstructor
public class Start implements Tile {

    private static final String DESCRIPTION_FORMAT = "Pass to earn %s %s!";

    @NonNull
    private final StartConfig startConfig;

    @Override
    public String getName() {
        return startConfig.getName();
    }

    @Override
    public String getDescription() {
        return format(DESCRIPTION_FORMAT, startConfig.getStartPassBonus(),
            Config.getConfig().getInventoryConfig().getBalanceCurrencyPlural());
    }

    @Override
    public void onPass(Actor actor) {
        // TODO - Add money to Actor
    }

    @Override
    public ActionCategory onLand(Actor actor) {
        // TODO - Add money to Actor?
        return null;
    }
}
