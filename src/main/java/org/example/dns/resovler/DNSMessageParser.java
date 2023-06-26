package org.example.dns.resovler;

import org.example.dns.model.Message;

import java.net.UnknownHostException;

public interface DNSMessageParser {
    Message parse(byte[] data);
}
