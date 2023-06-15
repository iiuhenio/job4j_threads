package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private int limit;

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == limit) {
            wait();
        }
        if (queue.size() == 0) {
            notifyAll();
        }
        queue.add((T) new SimpleBlockingQueue<Integer>());
    }

    public synchronized T poll() {
        while (queue.size() == 0) {
            notifyAll();
        }
        if (queue.size() == 1) {
            notifyAll();
        }
        return queue.remove();
    }
}
