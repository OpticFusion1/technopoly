package com.qub.Technopoly.config;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class FieldConfig {
    private final String name;
    private final String description;
    private PropertyConfig[] propertyConfigs;

    public FieldConfig() {
        name = "Field Name";
        description = "Field Description";
        propertyConfigs = new PropertyConfig[] {new PropertyConfig()};
    }
}
