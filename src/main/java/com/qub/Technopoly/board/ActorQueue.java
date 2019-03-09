package com.qub.Technopoly.board;

import com.qub.Technopoly.actor.Actor;

public class ActorQueue {
  private Actor[] actors;
  
  private int currentActorIndex = 0;
  private int currentSize = 0;
  
  public ActorQueue(int size) {
    actors = new Actor[size];
  }
  
  // TODO - Check currentSize, or Throw.
  public void addActor(Actor actor) {
    actors[currentSize] = actor;
    currentSize++;
  }
  
  // TODO - Find actor in array and remove them if exists
  public void removeActor(Actor actor) {
    
  }
  
  public Actor getNext() {
    var next = actors[currentActorIndex];
    
    currentActorIndex++;
    if(currentActorIndex >= actors.length) {
      currentActorIndex = 0;
    }
    
    return next;
  }
}
