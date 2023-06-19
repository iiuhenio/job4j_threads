package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {

    private final AtomicInteger count = new AtomicInteger();

    public void increment() {
        int next;
        int value;
        do {
            value = get();
            next = value + 1;
        } while (!count.compareAndSet(value, next));
    }

    public int get() {
        return count.get();
    }
}