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

    public void add(Ownable ownable) {
        if (owned.contains(ownable)) {
            throw new InventoryException(
                String.format(UNIQUE_ITEMS_ONLY_EXCEPTION_MSG_FORMAT, ownable.getName()));
        }
        owned.add(ownable);
    }


    public void add(int amount) {
        currentBalance += amount;
    }

    public void remove(Ownable ownable) {
        if (!owned.contains(ownable)) {
            throw new InventoryException(
                String.format(ITEM_DOES_NOT_EXIST_EXCEPTION_MSG_FORMAT, ownable.getName()));
        }

        owned.remove(ownable);
    }

    public boolean contains(Ownable ownable) {
        return owned.contains(ownable);
    }

    public <T extends Ownable> int getCountInInventory(Class<T> type) {
        return (int) owned.stream().filter(type::isInstance).count();
    }

    public <T extends Ownable> T[] getTypeInInventory(Class<T> type) {
        var ownablesGeneric = owned.stream().filter(type::isInstance).toArray();
        var ownablesOfType = (T[]) Array.newInstance(type, ownablesGeneric.length);

        for (var i = 0; i < ownablesOfType.length; i++) {
            ownablesOfType[i] = (T) ownablesGeneric[i];
        }

        return ownablesOfType;
    }

    public void remove(int amount) {
        currentBalance -= amount;
    }
}
