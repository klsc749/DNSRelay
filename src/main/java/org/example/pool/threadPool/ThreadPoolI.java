package org.example.pool.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolI implements ThreadPool{
    ExecutorService executor;

    public ThreadPoolI(int size) {
        executor = Executors.newFixedThreadPool(size);
    }

    @Override
    public void submit(Runnable task) {
        executor.submit(task);
    }
}
