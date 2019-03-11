package com.qub.Technopoly.util;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.actor.Player;
import com.qub.Technopoly.inventory.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

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
    public void addActorToCapacityDoesNotThrow() {
        for (var i = 0; i < CAPACITY; i++) {
            actorQueue.add(EXPECTED_ACTOR);
        }
    }

    @Test
    public void addActorOverCapacityThrowsArrayIndexOutOfBoundsException() {
        for (var i = 0; i < CAPACITY; i++) {
            actorQueue.add(EXPECTED_ACTOR);
        }
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> actorQueue.add(EXPECTED_ACTOR));
    }

    @Test
    public void addActorGetNextReturnsExpectedOrderOfActors() {
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

    @Test
    public void getBufferReturnsExpectedBuffer() {
        var underlyingBuffer = new Integer[] {1, 2, 3, 4, 5};
        var circularBuffer = new CircularBuffer<>(Integer.class, underlyingBuffer.length);
        Arrays.stream(underlyingBuffer).forEach(circularBuffer::add);
        assertArrayEquals(underlyingBuffer, circularBuffer.getBuffer());
    }

    @Test
    public void getCurrentPositionReturnsZeroOnCreated() {
        var circularBuffer = new CircularBuffer<>(Integer.class, 5);
        assertEquals(0, circularBuffer.getCurrentPosition());
    }

    @Test
    public void getCurrentPositionReturnsOneWhenCalledOnce() {
        var underlyingBuffer = new Integer[] {1, 2, 3, 4, 5};
        var circularBuffer = new CircularBuffer<>(Integer.class, underlyingBuffer.length);
        Arrays.stream(underlyingBuffer).forEach(circularBuffer::add);

        circularBuffer.getNext();
        assertEquals(1, circularBuffer.getCurrentPosition());
    }

    @Test
    public void getPreviousPositionReturnsLengthOnCreated() {
        var underlyingBuffer = new Integer[] {1, 2, 3, 4, 5};
        var circularBuffer = new CircularBuffer<>(Integer.class, underlyingBuffer.length);
        Arrays.stream(underlyingBuffer).forEach(circularBuffer::add);

        assertEquals(underlyingBuffer.length - 1, circularBuffer.getPreviousPosition());
    }

    @Test
    public void getPreviousPositionReturnsZeroWhenCalledOnce() {
        var underlyingBuffer = new Integer[] {1, 2, 3, 4, 5};
        var circularBuffer = new CircularBuffer<>(Integer.class, underlyingBuffer.length);
        Arrays.stream(underlyingBuffer).forEach(circularBuffer::add);

        circularBuffer.getNext();
        assertEquals(0, circularBuffer.getPreviousPosition());
    }

    @Test
    public void getPreviousPositionReturnsCurrentPositionMinusOne() {
        var underlyingBuffer = new Integer[] {1, 2, 3, 4, 5};
        var circularBuffer = new CircularBuffer<>(Integer.class, underlyingBuffer.length);
        Arrays.stream(underlyingBuffer).forEach(circularBuffer::add);

        circularBuffer.getNext();
        circularBuffer.getNext();

        assertEquals(circularBuffer.getCurrentPosition() - 1, circularBuffer.getPreviousPosition());
    }

    @Test
    public void setCurrentPositionCyclesPosition() {
        var underlyingBuffer = new Integer[] {1, 2, 3, 4, 5};
        var circularBuffer = new CircularBuffer<>(Integer.class, underlyingBuffer.length);
        Arrays.stream(underlyingBuffer).forEach(circularBuffer::add);

        for (var i = 0; i < VALIDATION_RUNS; i++) {
            circularBuffer.setCurrentPosition(i);
            var expectedIndex = i % underlyingBuffer.length;

            assertEquals(expectedIndex, circularBuffer.getCurrentPosition());
            circularBuffer.getNext();
        }
    }
}
