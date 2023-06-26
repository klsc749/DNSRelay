package org.example;



import org.example.dns.model.*;
import org.example.pool.datagramSocketPool.DatagramSocketPool;
import org.example.pool.datagramSocketPool.DatagramSocketPoolI;
import org.example.pool.redisConnectionPool.RedisConnectionPool;
import org.example.queryHanlder.DNSQueryHandler;
import org.example.queryHanlder.localHostHanlder.LocalHostHandler;
import org.example.queryHanlder.redisHandler.RedisHandler;
import org.example.queryHanlder.redisHandler.model.ValueWithTTL;
import org.example.queryHanlder.redisHandler.service.RedisService;
import org.example.queryHanlder.remoteServerHandler.RemoteServerHandler;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        RedisConnectionPool redisConnectionPool = new RedisConnectionPool();
        RedisService redisService = new RedisService(redisConnectionPool);
        redisService.setHostDNS("www.baidu.com", "4545", 5);

        DatagramSocketPool datagramSocketPool = new DatagramSocketPoolI(10);
        DNSQueryHandler handler = new RedisHandler(redisService);
        DNSQueryHandler remoteServerHandler = new RemoteServerHandler(datagramSocketPool, redisService);
        handler.setNext(remoteServerHandler);

        Question question = new Question();
        question.setName("www.baidu.com");
        question.setType(Type.A);
        question.setClassCode(1);

        Message message = handler.handle(question);
        System.out.println(message);
        question.setName("www.google.com");
        message = handler.handle(question);
        System.out.println(message);
        message = handler.handle(question);
        System.out.println(message);

    }
}