package com.qub.Technopoly.util;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.actor.Player;
import com.qub.Technopoly.inventory.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CircularBufferTest {

    private static final int CAPACITY = 5;
    private static final int VALIDATION_RUNS = 20;
    private static final Actor EXPECTED_ACTOR = new Player("Lars", new Inventory());

    private CircularBuffer<Actor> actorQueue;

    @BeforeEach
    public void setup() {
        actorQueue = new CircularBuffer(Actor.class, CAPACITY);
    }

    @Test
    public void addActorGetNextReturnsExpected() {
        actorQueue.add(EXPECTED_ACTOR);
        assertEquals(EXPECTED_ACTOR, actorQueue.getNext());
    }

    @Test
    public void AddActorToCapacityDoesNotThrow() {
        for (var i = 0; i < CAPACITY; i++) {
            actorQueue.add(EXPECTED_ACTOR);
        }
    }

    @Test
    public void AddActorOverCapacityThrowsArrayIndexOutOfBoundsException() {
        for (var i = 0; i < CAPACITY; i++) {
            actorQueue.add(EXPECTED_ACTOR);
        }
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> actorQueue.add(EXPECTED_ACTOR));
    }

    @Test
    public void AddActorGetNextReturnsExpectedOrderOfActors() {
        var expectedActors = new Actor[CAPACITY];
        for (var i = 0; i < CAPACITY; i++) {
            var newActor = new Player("Actor " + i, new Inventory());
            expectedActors[i] = newActor;
            actorQueue.add(newActor);
        }

        for (var i = 0; i < VALIDATION_RUNS; i++) {
            var actorIndex = i % CAPACITY;
            assertEquals(expectedActors[actorIndex], actorQueue.getNext());
        }
    }
}
