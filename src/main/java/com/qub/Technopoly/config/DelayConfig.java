package com.qub.Technopoly.config;

import com.qub.Technopoly.io.IOHelper;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * Configuration for the {@link IOHelper#doActionDelay()}
 */
@RequiredArgsConstructor
@Value
public class DelayConfig {
    private final float actionWait;

    public DelayConfig(){
        actionWait = 0.5f;
    }
}
