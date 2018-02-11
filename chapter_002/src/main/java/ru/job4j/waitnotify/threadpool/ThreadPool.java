package ru.job4j.waitnotify.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * ThreadPool.
 *
 * @author Roman Baranov (baranov.rp@gmail.com)
 * @version 1
 */
public class ThreadPool {

    private final BlockingQueue<Runnable> workQueue;
    private boolean isStopped = false;
    private final List<RunningThread> runningThreads = new ArrayList<>();

    public ThreadPool(BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            RunningThread t = new RunningThread();
            t.setDaemon(true);
            t.start();
            runningThreads.add(t);
        }
    }

    /**
     * Add work to wait queue.
     *
     * @param work
     */
    public void add(Work work) {
        workQueue.offer(work);
    }

    /**
     * Stop thread pool.
     */
    public void stop() {
        this.isStopped = true;
    }

    /**
     * Wrapper for runnable object.
     */
    private class RunningThread extends Thread {

        @Override
        public void run() {
            while (!isStopped || !workQueue.isEmpty()) {
                Runnable runnable;
                while ((runnable = workQueue.poll()) != null) {
                    runnable.run();
                    try {
                        Thread.sleep(1L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
