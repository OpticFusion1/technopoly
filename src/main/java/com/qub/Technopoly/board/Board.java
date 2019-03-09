package com.qub.Technopoly.board;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.util.CircularBuffer;

import static com.qub.Technopoly.constants.BoardConstants.MAX_PLAYERS;

public class Board {

  private CircularBuffer<Actor> actorQueue = new CircularBuffer<>(Actor.class, MAX_PLAYERS);
  
  public void addActor(Actor actor) {
    actorQueue.add(actor);
  }
  
  public Actor getNextActor() {
    return actorQueue.getNext();
  }

  public int getBoardActorCapacity(){
    return MAX_PLAYERS;
  }
}
