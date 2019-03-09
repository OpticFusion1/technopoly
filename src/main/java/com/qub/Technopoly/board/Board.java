package com.qub.Technopoly.board;
import com.qub.Technopoly.actor.Actor;

import static com.qub.Technopoly.constants.BoardConstants.MAX_PLAYERS;

public class Board {

  private ActorQueue actorQueue = new ActorQueue(MAX_PLAYERS);
  
  public void addActor(Actor actor) {
    actorQueue.addActor(actor);
  }
  
  public Actor getNextActor() {
    return actorQueue.getNext();
  }

  public int getBoardActorCapacity(){
    return MAX_PLAYERS;
  }
}
