package org.example.queryHanlder.remoteServerHandler;

import org.example.pool.datagramSocketPool.DatagramSocketPool;
import org.example.dns.model.*;
import org.example.dns.sender.DNSQuerySender;
import org.example.dns.sender.DNSQuerySenderI;
import org.example.queryHanlder.DNSQueryHandler;
import org.example.queryHanlder.redisHandler.service.RedisService;

import java.net.DatagramSocket;
import java.util.ArrayList;

public class RemoteServerHandler implements DNSQueryHandler {
    private DNSQueryHandler next;
    private DatagramSocketPool datagramSocketPool;

    private RedisService redisService;
    public RemoteServerHandler(DatagramSocketPool datagramSocketPool, RedisService redisService) {
        this.datagramSocketPool = datagramSocketPool;
        this.redisService = redisService;
    }

    @Override
    public void setNext(DNSQueryHandler next) {
        this.next = next;
    }

    @Override
    public Message handle(Question question) {
        System.out.println("RemoteServerHandler");
        Message message = new Message();
        Header header = new Header();
        header.setFlags(new Flag());
        header.getFlags().setQr(false);
        header.setQdCount(1);
        message.setHeader(header);
        message.setSections(new ArrayList<>());
        message.getSections().add(question);

        DNSQuerySender sender = new DNSQuerySenderI();

        Message response;
        DatagramSocket socket = null;
        try{
            socket = datagramSocketPool.getDatagramSocket();
            response = sender.sendQuery(message, socket);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally{
            if(socket != null){
                datagramSocketPool.returnDatagramSocket(socket);
            }
        }

        RRecord record = (RRecord)response.getSections().get(1);

        redisService.setHostDNS(question.getName(), record.getData(), record.getTtl());

        return response;
    }
}
