package org.example.dns.receiver;

import org.example.config.SystemConfig;
import org.example.pool.threadPool.ThreadPool;
import org.example.task.QueryTaskFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class DNSListenerI implements DNSListener{
    private final DatagramSocket datagramSocket;

    private ThreadPool threadPool;
    private QueryTaskFactory queryTaskFactory;

    public DNSListenerI(ThreadPool threadPool, QueryTaskFactory queryTaskFactory) throws SocketException {
        this.datagramSocket = new DatagramSocket(SystemConfig.LOCAL_DNS_PORT);
        this.threadPool = threadPool;
        this.queryTaskFactory = queryTaskFactory;
    }

    @Override
    public void listen() {
        byte[] buffer = new byte[512];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        while (true) {
            try {
                datagramSocket.receive(packet);
                System.out.println("收到请求");

                threadPool.submit(queryTaskFactory.createQueryTask(packet));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
