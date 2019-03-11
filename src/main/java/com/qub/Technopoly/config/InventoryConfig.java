package com.qub.Technopoly.config;

import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * Configuration for the {@link com.qub.Technopoly.inventory.Inventory} Tile
 */
@RequiredArgsConstructor
@Value
public class InventoryConfig {
    private final int startBalance;
    private final String currencyName;

    public InventoryConfig(){
        startBalance = 20000;
        currencyName = "dollars";
    }
}
