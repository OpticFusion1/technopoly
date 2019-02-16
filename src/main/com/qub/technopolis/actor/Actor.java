package main.com.qub.technopolis.actor;

public interface Actor {
  /**
   * Check whether it is this actors turn
   * @return
   */
  boolean IsActive();
  
  /**
   * Set this Actor active, i.e., it is this actor's turn.
   */
  void SetActive();
  
  /**
   * Set this Actor inactive, i.e., it is no longer this actor's turn.
   */
  void SetInactive();
  
  /**
   * Update the Actor
   * @return True if the actor updated, False is the actor did not update
   */
  boolean Update();
}
