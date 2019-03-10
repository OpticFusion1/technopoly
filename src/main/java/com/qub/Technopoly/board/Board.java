package com.qub.Technopoly.board;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.config.PlayerConfig;
import com.qub.Technopoly.util.CircularBuffer;

public class Board {

    private final PlayerConfig playerConfig = Config.getConfig().getPlayerConfig();

    private CircularBuffer<Actor> actorQueue =
        new CircularBuffer<>(Actor.class, playerConfig.getMaxPlayers());

    public void addActor(Actor actor) {
        actorQueue.add(actor);
    }

    public Actor getNextActor() {
        return actorQueue.getNext();
    }

    public int getBoardActorCapacity() {
        return actorQueue.length;
    }
}
