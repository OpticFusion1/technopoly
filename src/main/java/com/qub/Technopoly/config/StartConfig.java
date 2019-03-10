package com.qub.Technopoly.config;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class StartConfig {
    private final String name;
    private final int startPassBonus;
    private final int startLandOnBonus;
}
