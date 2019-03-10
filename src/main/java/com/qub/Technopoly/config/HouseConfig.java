package com.qub.Technopoly.config;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class HouseConfig {
    private final String houseName;
    private final int housePrice;
    private final int houseRent;

    // Default Configuration
    public HouseConfig(){
        houseName = "House Name";
        housePrice = 0;
        houseRent = 0;
    }
}
