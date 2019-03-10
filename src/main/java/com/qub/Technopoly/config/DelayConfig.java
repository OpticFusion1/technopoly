package com.qub.Technopoly.config;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class DelayConfig {
    private final float actionWait;

    public DelayConfig(){
        actionWait = 0.5f;
    }
}
