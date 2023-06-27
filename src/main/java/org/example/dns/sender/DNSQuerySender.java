package org.example.dns.sender;

import org.example.dns.model.Message;

import java.net.DatagramSocket;
import java.net.InetAddress;

public interface DNSQuerySender {
    Message sendQuery(Message message, DatagramSocket socket);

    void sendBack(Message message, DatagramSocket socket, InetAddress address, int port);
}
