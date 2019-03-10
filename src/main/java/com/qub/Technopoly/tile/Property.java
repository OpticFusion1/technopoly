package com.qub.Technopoly.tile;

import com.qub.Technopoly.actions.category.ActionCategory;
import com.qub.Technopoly.actions.category.OwnedPropertyActionCategory;
import com.qub.Technopoly.actions.category.UnownedPropertyActionCategory;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.config.HouseConfig;
import com.qub.Technopoly.config.PropertyConfig;
import com.qub.Technopoly.exception.GameStateException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class Property implements Tile, Ownable {

    @Getter
    private final PropertyConfig propertyConfig;

    @Getter
    private int currentHouses = 0;

    @Getter
    @Setter
    private Actor owner;

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

    public int getRent() {
        if (currentHouses == 0) {
            return propertyConfig.getPropertyRent();
        }

        return propertyConfig.getHouseConfigs()[currentHouses - 1].getHouseRent();
    }

    public int getPrice() {
        return propertyConfig.getPropertyPrice();
    }

    public HouseConfig getNextHouseInfo() {
        if (!canUpgrade()) {
            throw new GameStateException(
                "Can't upgrade the property as the property is already max upgraded");
        }
        return propertyConfig.getHouseConfigs()[currentHouses];
    }

    public void addHouse() {
        if (!canUpgrade()) {
            throw new GameStateException(
                "Can't add house to property as the property is already max upgraded");
        }
        currentHouses++;
    }

    public void removeHouse() {
        if (currentHouses <= 0) {
            throw new GameStateException(
                "Can't remove house from property as the property has no houses");
        }
        currentHouses--;
    }

    public boolean canUpgrade() {
        return currentHouses < propertyConfig.getHouseConfigs().length;
    }
}
