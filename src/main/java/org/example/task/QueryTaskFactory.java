package org.example.task;

import org.example.pool.datagramSocketPool.DatagramSocketPool;
import org.example.queryHanlder.DNSQueryHandler;

import java.net.DatagramPacket;

public class QueryTaskFactory {
    private DNSQueryHandler dnsQueryHandler;
    private DatagramSocketPool datagramSocketPool;

    public QueryTaskFactory(DNSQueryHandler dnsQueryHandler, DatagramSocketPool datagramSocketPool) {
        this.dnsQueryHandler = dnsQueryHandler;
        this.datagramSocketPool = datagramSocketPool;
    }

    public QueryTask createQueryTask(DatagramPacket packet){
        return new QueryTask(dnsQueryHandler, packet, datagramSocketPool);
    }

}
