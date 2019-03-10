package com.qub.Technopoly.config;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class FieldConfig {
    private final String fieldName;
    private final String fieldDescription;
    private PropertyConfig[] propertyConfigs;

    public FieldConfig() {
        fieldName = "Field Name";
        fieldDescription = "Field Description";
        propertyConfigs = new PropertyConfig[] {new PropertyConfig()};
    }
}
