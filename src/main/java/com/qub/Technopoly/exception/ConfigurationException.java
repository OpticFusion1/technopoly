package com.qub.Technopoly.exception;

import com.qub.Technopoly.config.Config;

/**
 * Exception thrown by {@link Config} when there is a problem with configuration.
 */
public class ConfigurationException extends RuntimeException {
    public ConfigurationException(String message) {
        super(message);
    }
}
