package main.com.qub.technopolis.board;

import java.util.LinkedList;
import java.util.Queue;
import main.com.qub.technopolis.actor.Actor;

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
