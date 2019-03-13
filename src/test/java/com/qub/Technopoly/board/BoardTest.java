package com.qub.Technopoly.board;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.actor.Player;
import com.qub.Technopoly.inventory.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {

    private static final int VALIDATION_RUNS = 20;
    private static final Actor EXPECTED_ACTOR = new Player("Lars", new Inventory());

    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    public void addActorGetNextReturnsExpected() {
        board.addActor(EXPECTED_ACTOR);
        assertEquals(EXPECTED_ACTOR, board.getNextActor());
    }

    @Test
    public void addActorToCapacityDoesNotThrow() {
        for (var i = 0; i < board.getBoardActorCapacity(); i++) {
            board.addActor(EXPECTED_ACTOR);
        }
    }

    @Test
    public void addActorOverCapacityThrowsArrayIndexOutOfBoundsException() {
        for (var i = 0; i < board.getBoardActorCapacity(); i++) {
            board.addActor(EXPECTED_ACTOR);
        }
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> board.addActor(EXPECTED_ACTOR));
    }

    @Test
    public void addActorGetNextReturnsExpectedOrderOfActors() {
        var expectedActors = new Actor[board.getBoardActorCapacity()];
        for (var i = 0; i < board.getBoardActorCapacity(); i++) {
            var newActor = new Player("Actor " + i, new Inventory());
            expectedActors[i] = newActor;
            board.addActor(newActor);
        }

        for (var i = 0; i < VALIDATION_RUNS; i++) {
            var actorIndex = i % board.getBoardActorCapacity();
            assertEquals(expectedActors[actorIndex], board.getNextActor());
        }
    }

    @Test
    public void getActorsWithMostMoneyReturnsExpectedActor(){
        var expectedActors = new Actor[board.getBoardActorCapacity()];
        for (var i = 0; i < board.getBoardActorCapacity(); i++) {
            var newActor = new Player("Actor " + i, new Inventory());
            expectedActors[i] = newActor;
            board.addActor(newActor);
        }

        var expectedActor = expectedActors[0];
        expectedActor.getInventory().add(1);

        var actualActors = board.getActorsWithMostMoney();

        assertEquals(1, actualActors.size());
        assertEquals(expectedActor, actualActors.get(0));
    }

    @Test
    public void getActorsWithMostMoneyReturnsExpectedActors(){
        var expectedActors = new Actor[board.getBoardActorCapacity()];
        for (var i = 0; i < board.getBoardActorCapacity(); i++) {
            var newActor = new Player("Actor " + i, new Inventory());
            expectedActors[i] = newActor;
            board.addActor(newActor);
        }

        var expectedActor = expectedActors[0];
        var expectedActor2 = expectedActors[1];
        expectedActor.getInventory().add(1);
        expectedActor2.getInventory().add(1);

        var actualActors = board.getActorsWithMostMoney();

        assertEquals(2, actualActors.size());
        assertEquals(expectedActor, actualActors.get(0));
        assertEquals(expectedActor2, actualActors.get(1));
    }
}
