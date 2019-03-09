package com.qub.Technopoly.board;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.actor.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActorQueueTest {

    private static final int CAPACITY = 5;
    private static final int VALIDATION_RUNS = 20;
    private static final Actor EXPECTED_ACTOR = new Player("Lars");

    private ActorQueue actorQueue;

    @BeforeEach
    public void setup() {
        actorQueue = new ActorQueue(CAPACITY);
    }

    @Test
    public void addActorGetNextReturnsExpected() {
        actorQueue.addActor(EXPECTED_ACTOR);
        assertEquals(EXPECTED_ACTOR, actorQueue.getNext());
    }

    @Test
    public void AddActorToCapacityDoesNotThrow() {
        for (var i = 0; i < CAPACITY; i++) {
            actorQueue.addActor(EXPECTED_ACTOR);
        }
    }

    @Test
    public void AddActorOverCapacityThrowsArrayIndexOutOfBoundsException() {
        for (var i = 0; i < CAPACITY; i++) {
            actorQueue.addActor(EXPECTED_ACTOR);
        }
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> actorQueue.addActor(EXPECTED_ACTOR));
    }

    @Test
    public void AddActorGetNextReturnsExpectedOrderOfActors() {
        var expectedActors = new Actor[CAPACITY];
        for (var i = 0; i < CAPACITY; i++) {
            var newActor = new Player("Actor " + i);
            expectedActors[i] = newActor;
            actorQueue.addActor(newActor);
        }

        for (var i = 0; i < VALIDATION_RUNS; i++) {
            var actorIndex = i % CAPACITY;
            assertEquals(expectedActors[actorIndex], actorQueue.getNext());
        }
    }
}
