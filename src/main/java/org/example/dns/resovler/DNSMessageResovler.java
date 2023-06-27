package org.example.dns.resovler;

import org.example.dns.model.Message;

import java.net.UnknownHostException;

public class DNSMessageResovler {
    private static DNSMessageParser dnsMessageParser = new DNSMessageParserI();
    private static DNSMessageSerializer dnsMessageSerializer = new DNSMessageSerializerI();
    private DNSMessageResovler() {
    }

    public static Message parse(byte[] bytes) {
        return dnsMessageParser.parse(bytes);
    }

    public static byte[] serialize(Message message) throws UnknownHostException {
        return dnsMessageSerializer.serialize(message);
    }
}
