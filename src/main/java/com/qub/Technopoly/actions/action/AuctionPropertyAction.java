package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import lombok.NonNull;

public class AuctionPropertyAction implements Action {

    private static String NAME = "Auction Property";
    private static String DESCRIPTION = "Don't want the property? Can't afford it? Auction it!";

    private OutputSource outputSource = IOHelper.getOutputSource();

    @Override
    public @NonNull String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public boolean execute() {
        outputSource.writeBody(NAME + " is not implemented");
        return false;
    }
}
