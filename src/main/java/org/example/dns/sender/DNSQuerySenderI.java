package org.example.dns.sender;

import org.example.config.SystemConfig;
import org.example.dns.model.Message;
import org.example.dns.resovler.*;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DNSQuerySenderI implements DNSQuerySender {

    public DNSQuerySenderI() {
    }
    @Override
    public Message sendQuery(Message message, DatagramSocket socket) {
        try {
            byte[] query = DNSMessageResovler.serialize(message);
            socket.send(new DatagramPacket(query, query.length, InetAddress.getByName(SystemConfig.ROOT_SERVER_IP), Integer.parseInt(SystemConfig.ROOT_SERVER_PORT)));
            byte[] buffer = new byte[512];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket);
            return DNSMessageResovler.parse(buffer);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sendBack(Message message, DatagramSocket socket, InetAddress address, int port) {
        try {
            byte[] response = DNSMessageResovler.serialize(message);
            socket.send(new DatagramPacket(response, response.length, address, port));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
