package org.example.dns.sender;

import org.example.config.SystemConfig;
import org.example.dns.model.Message;
import org.example.dns.resovler.*;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DNSQuerySenderI implements DNSQuerySender {
    private DNSMessageParser parser;
    private DNSMessageSerializer serializer;

    public DNSQuerySenderI() {
        this.parser = new DNSMessageParserI();
        this.serializer = new DNSMessageSerializerI();
    }
    @Override
    public Message sendQuery(Message message, DatagramSocket socket) {
        try {
            byte[] query = serializer.serialize(message);
            socket.send(new DatagramPacket(query, query.length, InetAddress.getByName(SystemConfig.ROOT_SERVER_IP), Integer.parseInt(SystemConfig.ROOT_SERVER_PORT)));
            byte[] buffer = new byte[512];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket);
            return parser.parse(buffer);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
