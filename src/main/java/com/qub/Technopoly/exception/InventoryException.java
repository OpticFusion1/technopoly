package com.qub.Technopoly.exception;

import com.qub.Technopoly.inventory.Inventory;

/**
 * Exception used by {@link Inventory} on error
 */
public class InventoryException extends RuntimeException {
    public InventoryException(String message) {
        super(message);
    }
}
