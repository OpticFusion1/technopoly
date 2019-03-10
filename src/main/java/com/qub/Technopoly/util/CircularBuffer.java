package com.qub.Technopoly.util;

import static java.lang.reflect.Array.newInstance;

public class CircularBuffer<T> {
    private T[] buffer;

    private int currentIndex = 0;
    private int currentSize = 0;

    public CircularBuffer(Class<T> clazz, int size) {
        buffer = (T[]) newInstance(clazz, size);
    }

    // TODO - Check currentSize, or Throw.
    public void add(T newT) {
        buffer[currentSize] = newT;
        currentSize++;
    }

    public T getNext() {
        var next = buffer[currentIndex];

        currentIndex++;
        if (currentIndex >= buffer.length) {
            currentIndex = 0;
        }

        return next;
    }
}
