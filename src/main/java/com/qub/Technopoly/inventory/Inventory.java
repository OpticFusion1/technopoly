package com.qub.Technopoly.inventory;

import com.qub.Technopoly.exception.InventoryException;
import com.qub.Technopoly.tile.Ownable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;

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
    private int currentBalance;

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

    public void remove(int amount) {
        currentBalance -= amount;
    }
}
