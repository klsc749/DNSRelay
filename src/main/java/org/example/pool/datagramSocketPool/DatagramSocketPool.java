package org.example.pool.datagramSocketPool;

import java.net.DatagramSocket;

public interface DatagramSocketPool {
    DatagramSocket getDatagramSocket() throws InterruptedException;
    void returnDatagramSocket(DatagramSocket datagramSocket);
}
