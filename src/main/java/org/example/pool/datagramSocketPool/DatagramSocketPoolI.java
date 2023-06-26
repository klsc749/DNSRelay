package org.example.pool.datagramSocketPool;

import java.net.DatagramSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DatagramSocketPoolI implements DatagramSocketPool{
    private final BlockingQueue<DatagramSocket> pool;

    public DatagramSocketPoolI(int poolSize) {
        pool = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            try {
                pool.add(new DatagramSocket());
            } catch (Exception e) {
                throw new RuntimeException("Could not create DatagramSocket", e);
            }
        }
    }
    @Override
    public DatagramSocket getDatagramSocket() throws InterruptedException {
        return pool.take();
    }

    @Override
    public void returnDatagramSocket(DatagramSocket datagramSocket) {
        pool.add(datagramSocket);
    }
}
