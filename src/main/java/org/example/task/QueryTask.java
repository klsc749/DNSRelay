package org.example.task;

import org.example.pool.datagramSocketPool.DatagramSocketPool;
import org.example.dns.model.Message;
import org.example.dns.sender.DNSQuerySender;
import org.example.dns.sender.DNSQuerySenderI;

public class QueryTask implements Runnable{
    private DatagramSocketPool datagramSocketPool;
    private Message message;

    private DNSQuerySender dnsQuerySender = new DNSQuerySenderI();

    public QueryTask(DatagramSocketPool datagramSocketPool, Message message) {
        this.datagramSocketPool = datagramSocketPool;
        this.message = message;
    }


    @Override
    public void run() {

    }
}
