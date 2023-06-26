package org.example.dns.sender;

import org.example.dns.model.Message;

import java.net.DatagramSocket;

public interface DNSQuerySender {
    Message sendQuery(Message message, DatagramSocket socket);
}
