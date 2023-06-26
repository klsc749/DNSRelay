package org.example.dns.resovler;

import org.example.dns.model.Message;

import java.net.UnknownHostException;

public interface DNSMessageSerializer {
    byte[] serialize(Message message) throws UnknownHostException;
}
