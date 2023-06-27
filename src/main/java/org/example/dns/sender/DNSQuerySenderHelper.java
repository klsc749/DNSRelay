package org.example.dns.sender;

import org.example.dns.model.Message;
import org.example.dns.model.Question;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class DNSQuerySenderHelper {
    private static DNSQuerySender dnsQuerySender = new DNSQuerySenderI();

    private DNSQuerySenderHelper() {
    }

    public static Message sendQuery(Message message, DatagramSocket socket) {
        return dnsQuerySender.sendQuery(message, socket);
    }

    public  static void sendBack(Message message, DatagramSocket socket, InetAddress address, int port) {
        dnsQuerySender.sendBack(message, socket, address, port);
    }
}
