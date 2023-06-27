package org.example.dns;

import org.example.config.SystemConfig;
import org.example.dns.receiver.DNSListener;
import org.example.dns.receiver.DNSListenerI;
import org.example.dns.sender.DNSQuerySenderHelper;
import org.example.pool.datagramSocketPool.DatagramSocketPool;
import org.example.pool.datagramSocketPool.DatagramSocketPoolI;
import org.example.pool.redisConnectionPool.RedisConnectionPool;
import org.example.pool.threadPool.ThreadPool;
import org.example.pool.threadPool.ThreadPoolI;
import org.example.queryHanlder.DNSQueryHandler;
import org.example.queryHanlder.localHostHanlder.LocalHostHandler;
import org.example.queryHanlder.redisHandler.RedisHandler;
import org.example.queryHanlder.redisHandler.service.RedisService;
import org.example.queryHanlder.remoteServerHandler.RemoteServerHandler;
import org.example.task.QueryTaskFactory;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class DNSRelay {
    private ThreadPool threadPool;
    private RedisConnectionPool redisConnectionPool;
    private RedisService redisService;
    private DatagramSocketPool datagramSocketPool;
    private DNSQueryHandler dnsQueryHandler;
    private QueryTaskFactory queryTaskFactory;
    private DNSListener dnsListener;

    private DatagramSocket listenerSocket;

    public DNSRelay() throws SocketException {
        redisConnectionPool = new RedisConnectionPool();
        redisService = new RedisService(redisConnectionPool);
        datagramSocketPool = new DatagramSocketPoolI(10);

        DNSQueryHandler localHostHandler = new LocalHostHandler();
        DNSQueryHandler redisHandler = new RedisHandler(redisService);
        DNSQueryHandler remoteServerHandler = new RemoteServerHandler(datagramSocketPool, redisService);

        localHostHandler.setNext(redisHandler);
        redisHandler.setNext(remoteServerHandler);
        dnsQueryHandler = localHostHandler;

        queryTaskFactory = new QueryTaskFactory(dnsQueryHandler, datagramSocketPool);
        threadPool = new ThreadPoolI(10);
        InetSocketAddress address = new InetSocketAddress("0.0.0.0", SystemConfig.LOCAL_DNS_PORT);
        listenerSocket = new DatagramSocket(address);
        dnsListener = new DNSListenerI(threadPool, queryTaskFactory, listenerSocket);
        DNSQuerySenderHelper.setSendSocket(listenerSocket);
    }

    public void start(){
        dnsListener.listen();
    }

}
