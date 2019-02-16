package main.com.qub.technopolis.actor;

import java.io.IOException;
import java.util.Scanner;

public class Player implements Actor {

  private String playerName;
  private boolean isActive = false;


  public Player(String playerName) {
    this.playerName = playerName;
  }

  public String getPlayerName() {
    return playerName;
  }

  @Override
  public boolean IsActive() {
    return isActive;
  }

  @Override
  public void SetActive() {
    // TODO Auto-generated method stub
    isActive = true;
  }

  @Override
  public void SetInactive() {
    // TODO Auto-generated method stub
    isActive = false;
  }

  @Override
  public boolean Update() {
    // TODO THIS IS EXAMPLE CODE.
    System.out.printf("It is %s's turn!\n", playerName);
    
    // We will not be using thread.sleep :)
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // We are done
    return true;
  }
}
