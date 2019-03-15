package com.qub.Technopoly.tile;

import com.qub.Technopoly.actions.group.ActionGroup;
import com.qub.Technopoly.actions.group.OwnedPropertyActionGroup;
import com.qub.Technopoly.actions.group.UnownedPropertyActionGroup;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.config.HouseConfig;
import com.qub.Technopoly.config.PropertyConfig;
import com.qub.Technopoly.exception.GameStateException;
import com.qub.Technopoly.util.Field;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Contains information about a property (monopoly street) that a user can own
 */
@RequiredArgsConstructor
public class Property implements Tile, Ownable {

    @Getter
    private final PropertyConfig propertyConfig;

    @Getter
    private int currentHouses = 0;

    @Getter
    @Setter
    private Actor owner;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return propertyConfig.getPropertyName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return propertyConfig.getPropertyDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPass(Actor actor) {
        // Do Nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionGroup onLand(Actor actor, Board board) {
        var owner = getOwner();
        return owner == null ?
            new UnownedPropertyActionGroup(actor, this, board) :
            new OwnedPropertyActionGroup(actor, this);
    }

    /**
     * Calculates the rent that needs to be paid for landing on this property.
     * <p>The rent factors in how many houses are on the property</p>
     * @return The rent that must be paid when landing on this property
     */
    public int getRent() {
        if (currentHouses == 0) {
            return propertyConfig.getPropertyRent();
        }

        return propertyConfig.getHouseConfigs()[currentHouses - 1].getHouseRent();
    }

    /**
     * Gets the price of this property that the user has to pay to buy it.
     * @return The price to purchase the property
     */
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

    /**
     * Adds a house to the property
     * @throws GameStateException If the property is maximum upgraded
     */
    public void addHouse() {
        if (!canUpgrade()) {
            throw new GameStateException(
                "Can't add house to property as the property is already max upgraded");
        }
        currentHouses++;
    }

    /**
     * Removes a house from the property
     * @throws GameStateException If the property has no houses
     */
    public void removeHouse() {
        if (currentHouses <= 0) {
            throw new GameStateException(
                "Can't remove house from property as the property has no houses");
        }
        currentHouses--;
    }

    /**
     * Check whether or not it is possible to upgrade this property
     * @return Whether we can upgrade the property
     */
    public boolean canUpgrade() {
        return currentHouses < propertyConfig.getHouseConfigs().length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[Field: " + Field.getFieldForProperty(this).getName() + " Property: " + getName() + "]";
    }
}
