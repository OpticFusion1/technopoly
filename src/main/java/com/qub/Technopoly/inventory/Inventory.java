package com.qub.Technopoly.inventory;

import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.exception.InventoryException;
import com.qub.Technopoly.tile.Ownable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an instance of an inventory which can contain various items
 */
@RequiredArgsConstructor
public class Inventory {
    private static final String UNIQUE_ITEMS_ONLY_EXCEPTION_MSG_FORMAT =
        "Can't add %s to inventory, as the inventory already has this item.";
    private static final String ITEM_DOES_NOT_EXIST_EXCEPTION_MSG_FORMAT =
        "Can't remove %s from inventory, as the inventory does not contain this item.";
    private final List<Ownable> owned = new ArrayList<>();
    @NonFinal
    @Getter
    private int currentBalance = Config.getConfig().getInventoryConfig().getStartBalance();

    /**
     * Adds a new {@link Ownable} to this inventory
     * @param ownable The ownable to add to the inventory
     */
    public void add(Ownable ownable) {
        if (owned.contains(ownable)) {
            throw new InventoryException(
                String.format(UNIQUE_ITEMS_ONLY_EXCEPTION_MSG_FORMAT, ownable.getName()));
        }
        owned.add(ownable);
    }

    /**
     * Adds currency to this inventory
     * @param amount The amount (currency) to add to the inventory
     */
    public void add(int amount) {
        currentBalance += amount;
    }

    /**
     * Removes an {@link Ownable} from this inventory
     * @param ownable The ownable to remove from the inventory
     */
    public void remove(Ownable ownable) {
        if (!owned.contains(ownable)) {
            throw new InventoryException(
                String.format(ITEM_DOES_NOT_EXIST_EXCEPTION_MSG_FORMAT, ownable.getName()));
        }

        owned.remove(ownable);
    }

    /**
     * Check whether the inventory contains a specific {@link Ownable}
     * @param ownable The ownable to check if it exists in this inventory
     * @return Whether the inventory contains the ownable, or not
     */
    public boolean contains(Ownable ownable) {
        return owned.contains(ownable);
    }

    /**
     * Get the amount of a specific type of {@link Ownable} in the inventory
     * @param type The type of {@link Ownable} to check the count for
     * @param <T> The type of {@link Ownable} to check the count for
     * @return The count of items of the supplied type
     */
    public <T extends Ownable> int getCountInInventory(Class<T> type) {
        return (int) owned.stream().filter(type::isInstance).count();
    }

    /**
     * Gets all {@link Ownable}s of a specific type from the inventory
     * @param type The type of {@link Ownable} to get
     * @param <T> The type of {@link Ownable} to get
     * @return All items of supplied type
     */
    public <T extends Ownable> T[] getTypeInInventory(Class<T> type) {
        var ownablesGeneric = owned.stream().filter(type::isInstance).toArray();
        var ownablesOfType = (T[]) Array.newInstance(type, ownablesGeneric.length);

        for (var i = 0; i < ownablesOfType.length; i++) {
            ownablesOfType[i] = (T) ownablesGeneric[i];
        }

        return ownablesOfType;
    }

    /**
     * Removes currency from the inventory
     * @param amount The amount to remove from the inventory
     */
    public void remove(int amount) {
        currentBalance -= amount;
    }
}
