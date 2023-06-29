package org.example.task;

import org.example.config.SystemConfig;
import org.example.dns.model.Question;
import org.example.dns.model.Message;
import org.example.dns.resovler.DNSMessageParser;
import org.example.dns.resovler.DNSMessageParserI;
import org.example.dns.sender.DNSQuerySenderHelper;
import org.example.pool.datagramSocketPool.DatagramSocketPool;
import org.example.queryHanlder.DNSQueryHandler;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class QueryTask implements Runnable{
    private final DNSQueryHandler handler;

    private final DatagramPacket packet;

    private final DatagramSocketPool datagramSocketPool;

    public QueryTask(DNSQueryHandler dnsQueryHandler, DatagramPacket packet, DatagramSocketPool datagramSocketPool) {
        this.handler = dnsQueryHandler;
        this.packet = packet;
        this.datagramSocketPool = datagramSocketPool;
    }


    @Override
    public void run() {
        DNSMessageParser parser = new DNSMessageParserI();
        Message message = parser.parse(packet.getData());
        Question question = (Question) message.getSections().get(0);

        if(SystemConfig.ENABLE_DEBUG){
            System.out.println(Thread.currentThread().getName() + " QueryTask handle " + question.getName());
        }

        Message result = handler.handle(question);
        if(result == null){
            return;
        }
        result.getHeader().setId(message.getHeader().getId());
        result.getHeader().getFlags().setRd(message.getHeader().getFlags().isRd());
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = datagramSocketPool.getDatagramSocket();
            if(SystemConfig.ENABLE_DEBUG){
                System.out.println(Thread.currentThread().getName() + " QueryTask sendBack to " + packet.getAddress() + ":" + packet.getPort() + " Response : " + result);
            }
            DNSQuerySenderHelper.sendBack(result, packet.getAddress(), packet.getPort());
            if(SystemConfig.ENABLE_DEBUG){
                System.out.println(Thread.currentThread().getName() + " QueryTask sendBack Complete");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally{
            if(datagramSocket != null){
                datagramSocketPool.returnDatagramSocket(datagramSocket);
            }
        }
    }
}
