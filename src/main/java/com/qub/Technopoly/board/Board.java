package com.qub.Technopoly.board;
import com.qub.Technopoly.actor.Actor;

public class Board {
  
  private static final int MAX_PLAYERS = 4;
  
  private ActorQueue actorQueue = new ActorQueue(MAX_PLAYERS);
  
  public void AddActor(Actor actor) {
    actorQueue.addActor(actor);
  }
  
  public Actor getNextActor() {
    return actorQueue.getNext();
  }
}
