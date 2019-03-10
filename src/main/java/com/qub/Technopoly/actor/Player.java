package com.qub.Technopoly.actor;

import com.qub.Technopoly.inventory.Inventory;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Player implements Actor {

    @Getter
    @NonNull
    private final String actorName;
    @Getter
    @NonNull
    private final Inventory inventory;

    private boolean isActive = false;

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
        System.out.printf("It is %s's turn!\n", actorName);

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
