package com.qub.Technopoly.util;

import static java.lang.reflect.Array.newInstance;

/**
 * The circular buffer class is a utility that allows items to cycle. (1 - 2 - 3 - 4 - 5 - 1 -2 -3 -4 -5 ....)
 *
 * @param <T> The type of item the circular buffer should contain
 */
public class CircularBuffer<T> {

    public final int length;

    private T[] buffer;
    private int currentIndex = 0;
    private int currentSize = 0;

    private boolean firstRun = true;

    /**
     * Constructs a new circular buffer of type T initialized to specified size
     *
     * @param clazz The type of item the circular buffer should store
     * @param size  The size of the buffer
     */
    public CircularBuffer(Class<T> clazz, int size) {
        buffer = (T[]) newInstance(clazz, size);
        length = buffer.length;
    }

    /**
     * Adds a new item to the circular buffer
     *
     * @param newT The item to add
     */
    public void add(T newT) {
        buffer[currentSize] = newT;
        currentSize++;
    }

    /**
     * Gets the next item in the circular buffer
     *
     * @return The next item in the buffer
     */
    public T getNext() {
        var next = buffer[currentIndex];
        currentIndex++;
        if (currentIndex >= buffer.length) {
            currentIndex = 0;
        }

        return next;
    }

    /**
     * Sets the current position of the circularbuffer. The position is cycled with the modulo operator to prevent out of range exception.
     *
     * @param position The position to set the buffer to
     */
    public void setCurrentPosition(int position) {
        currentIndex = position % buffer.length;
    }

    /**
     * Gets the current position of the circular buffer
     *
     * @return The current position of the circular buffer
     */
    public int getCurrentPosition() {
        return currentIndex;
    }

    /**
     * Returns the previous position of the circular buffer
     *
     * @return The previous position of the circular buffer
     */
    public int getPreviousPosition() {
        var position = getCurrentPosition() - 1;
        if (position == -1) {
            position = buffer.length - 1;
        }
        return position;
    }

    /**
     * Gets the underlying array that the buffer uses
     *
     * @return The underlying buffer
     */
    public T[] getBuffer() {
        return buffer;
    }
}
