package org.example.pool.datagramSocketPool;

import java.net.DatagramSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DatagramSocketPoolI implements DatagramSocketPool{
    private final BlockingQueue<DatagramSocket> pool;

    public DatagramSocketPoolI(int poolSize) {
        pool = new ArrayBlockingQueue<>(poolSize);
        int startPort = 30000;
        for (int i = 0; i < poolSize; i++) {
            try {
                int port = startPort + i;
                if (port > 31000) {
                    throw new RuntimeException("Not enough ports in the range 30000-31000 for the requested pool size");
                }
                pool.add(new DatagramSocket(port));
            } catch (Exception e) {
                throw new RuntimeException("Could not create DatagramSocket on port " + (startPort + i), e);
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
