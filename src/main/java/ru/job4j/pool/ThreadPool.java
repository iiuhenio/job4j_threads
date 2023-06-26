package ru.job4j.pool;

import ru.job4j.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(4);

    public ThreadPool(int size) throws InterruptedException {
        this.size = size;
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread();
            threads.add(thread);
        }
        for (Thread thread : threads) {
           thread.start();
           tasks.poll();
           if (tasks.isEmpty()) {
               thread.wait();
           } else {
               notifyAll();
           }
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}