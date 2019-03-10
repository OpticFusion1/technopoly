package com.qub.Technopoly.board;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.util.CircularBuffer;

public class Board {

    private CircularBuffer<Actor> actorQueue =
        new CircularBuffer<>(Actor.class, Config.getConfig().getPlayerConfig().getMaxPlayers());

    public void addActor(Actor actor) {
        actorQueue.add(actor);
    }

    public void addActors(Actor[] actors) {
        actorQueue = new CircularBuffer<>(Actor.class, actors.length);
        for (var i = 0; i < actors.length; i++) {
            addActor(actors[i]);
        }
    }

    public Actor getNextActor() {
        return actorQueue.getNext();
    }

    public int getBoardActorCapacity() {
        return actorQueue.length;
    }
}
