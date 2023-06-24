package org.example.threadPool;

public interface ThreadPool {
        void execute(Runnable task);
        void stop();
}
