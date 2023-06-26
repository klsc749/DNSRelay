package org.example.dns.resovler;

import org.example.dns.model.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;

public class DNSMessageParserTest {
    String hexString = "0002818000010002000000000377777704627570740365647502636e0000010001c00c000500010000045f000704766e3436c010c02d000100010000045b00040a0309a1";

    @Test
    public void testParse() throws UnknownHostException {

    // Convert hex string to byte array
        byte[] data = hexStringToByteArray(hexString);

        DNSMessageParser parser = new DNSMessageParserI();
        Message message = parser.parse(data);

        Assertions.assertEquals(message.getHeader().getId(), 2);
        Assertions.assertTrue(message.getHeader().getFlags().isQr());
        Assertions.assertEquals(message.getHeader().getFlags().getOpcode(), 0);
        Assertions.assertFalse(message.getHeader().getFlags().isAa());
        Assertions.assertFalse(message.getHeader().getFlags().isTc());
        Assertions.assertTrue(message.getHeader().getFlags().isRd());
        Assertions.assertTrue(message.getHeader().getFlags().isRa());
        Assertions.assertEquals(message.getHeader().getFlags().getZ(), 0);
        Assertions.assertEquals(message.getHeader().getFlags().getRcode(), 0);

        Assertions.assertEquals(message.getHeader().getQdCount(), 1);
        Assertions.assertEquals(message.getHeader().getAnCount(), 2);
        Assertions.assertEquals(message.getHeader().getNsCount(), 0);
        Assertions.assertEquals(message.getHeader().getArCount(), 0);
    }

    @Test
    public void testSerialize() throws UnknownHostException {
        byte[] data = hexStringToByteArray(hexString);

        DNSMessageSerializer serializer = new DNSMessageSerializerI();
        DNSMessageParser parser = new DNSMessageParserI();
        Message message = parser.parse(data);

        byte[] serializedData = serializer.serialize(message);
        Message message1 = parser.parse(serializedData);

        Assertions.assertEquals(message.getHeader().getId(), message1.getHeader().getId());
        Assertions.assertEquals(message.getHeader().getFlags().isQr(), message1.getHeader().getFlags().isQr());
        Assertions.assertEquals(message.getHeader().getFlags().getOpcode(), message1.getHeader().getFlags().getOpcode());
        Assertions.assertEquals(message.getHeader().getFlags().isAa(), message1.getHeader().getFlags().isAa());
        Assertions.assertEquals(message.getHeader().getFlags().isTc(), message1.getHeader().getFlags().isTc());
        Assertions.assertEquals(message.getHeader().getFlags().isRd(), message1.getHeader().getFlags().isRd());
        Assertions.assertEquals(message.getHeader().getFlags().isRa(), message1.getHeader().getFlags().isRa());
        Assertions.assertEquals(message.getHeader().getFlags().getZ(), message1.getHeader().getFlags().getZ());
        Assertions.assertEquals(message.getHeader().getFlags().getRcode(), message1.getHeader().getFlags().getRcode());

        Assertions.assertEquals(message.getHeader().getQdCount(), message1.getHeader().getQdCount());
        Assertions.assertEquals(message.getHeader().getAnCount(), message1.getHeader().getAnCount());
        Assertions.assertEquals(message.getHeader().getNsCount(), message1.getHeader().getNsCount());
        Assertions.assertEquals(message.getHeader().getArCount(), message1.getHeader().getArCount());


    }

    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
