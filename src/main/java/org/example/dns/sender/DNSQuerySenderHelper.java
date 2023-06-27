package org.example.dns.sender;

import org.example.dns.model.Message;
import org.example.dns.model.Question;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class DNSQuerySenderHelper {
    private static DNSQuerySender dnsQuerySender = new DNSQuerySenderI();

    private static DatagramSocket sendSocket;

    private DNSQuerySenderHelper() {
    }

    public static void setSendSocket(DatagramSocket sendSocket) {
        DNSQuerySenderHelper.sendSocket = sendSocket;
    }

    public static Message sendQuery(Message message) {
        return dnsQuerySender.sendQuery(message, sendSocket);
    }

    public static Message sendQuery(Message message, DatagramSocket socket) {
        return dnsQuerySender.sendQuery(message, socket);
    }

    public  static void sendBack(Message message, DatagramSocket socket, InetAddress address, int port) {
        dnsQuerySender.sendBack(message, socket, address, port);
    }

    public static void sendBack(Message message, InetAddress address, int port) {
        dnsQuerySender.sendBack(message, sendSocket, address, port);
    }
}
