package com.qub.Technopoly.tile;

import com.qub.Technopoly.actions.category.ActionCategory;
import com.qub.Technopoly.actions.category.OwnedPropertyActionCategory;
import com.qub.Technopoly.actions.category.UnownedPropertyActionCategory;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.config.PropertyConfig;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Property implements Tile, Ownable {

    private final PropertyConfig propertyConfig;

    @Override
    public String getName() {
        return propertyConfig.getPropertyName();
    }

    @Override
    public String getDescription() {
        return propertyConfig.getPropertyDescription();
    }

    @Override
    public void onPass(Actor actor) {
        // Do Nothing
    }

    @Override
    public ActionCategory onLand(Actor actor) {
        var owner = getOwner();
        return owner == null ?
            new UnownedPropertyActionCategory(actor, this) :
            new OwnedPropertyActionCategory(actor, this);
    }

    @Override
    public Actor getOwner() {
        return null;
    }

    public int getRent() {
        // TODO - Calculate rent
        return propertyConfig.getPropertyRent();
    }

    public int getPrice() {
        return propertyConfig.getPropertyPrice();
    }
}
