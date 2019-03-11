package com.qub.Technopoly.exception;

import com.qub.Technopoly.Game;

/**
 * Exception used by {@link Game} and other classes when a fatal state exception occurs (Developer Error)
 */
public class GameStateException extends RuntimeException {
    public GameStateException(String message) {
        super(message);
    }
}
