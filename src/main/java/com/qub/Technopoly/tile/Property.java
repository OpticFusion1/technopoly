package com.qub.Technopoly.tile;

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

    public String getDescription() {
        return propertyConfig.getPropertyDescription();
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
