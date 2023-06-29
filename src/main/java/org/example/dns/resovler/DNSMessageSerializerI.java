package org.example.dns.resovler;

import org.example.dns.model.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class DNSMessageSerializerI implements DNSMessageSerializer{
    @Override
    public byte[] serialize(Message message) throws UnknownHostException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.order(ByteOrder.BIG_ENDIAN);

        // Serialize header
        serializeHeader(buffer, message.getHeader());

        // Serialize sections
        for (Section section : message.getSections()) {
            serializeSection(buffer, section);
        }

        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        return data;
    }

    private void serializeHeader(ByteBuffer buffer, Header header) {
        // Serialize ID
        buffer.putShort((short) header.getId());

        // Serialize flags
        Flag flags = header.getFlags();
        int flagsValue = (flags.isQr() ? 0x8000 : 0)
                | (flags.getOpcode() << 11)
                | (flags.isAa() ? 0x0400 : 0)
                | (flags.isTc() ? 0x0200 : 0)
                | (flags.isRd() ? 0x0100 : 0)
                | (flags.isRa() ? 0x0080 : 0)
                | (flags.getZ() << 4)
                | flags.getRcode();
        buffer.putShort((short) flagsValue);

        // Serialize counts
        buffer.putShort((short) header.getQdCount());
        buffer.putShort((short) header.getAnCount());
        buffer.putShort((short) header.getNsCount());
        buffer.putShort((short) header.getArCount());
    }

    private void serializeSection(ByteBuffer buffer, Section section) throws UnknownHostException {
        // Serialize name
        serializeName(buffer, section.getName());

        // Serialize type and class code
        buffer.putShort((short) section.getType().getValue());
        buffer.putShort((short) section.getClassCode());

        if (section instanceof RRecord) {
            RRecord record = (RRecord) section;

            // Serialize TTL and data length
            buffer.putInt(record.getTtl());
            buffer.putShort((short) record.getDataLength());

            // Serialize data
            serializeData(buffer, record.getType(), record.getData());
        }
    }

    private void serializeName(ByteBuffer buffer, String name) {
        String[] labels = name.split("\\.");
        for (String label : labels) {
            buffer.put((byte) label.length());
            buffer.put(label.getBytes(StandardCharsets.UTF_8));
        }
        buffer.put((byte) 0); // End of name
    }

    private void serializeData(ByteBuffer buffer, Type type, String data) throws UnknownHostException {
        switch (type) {
            case A, AAAA:
                // Convert IP address to bytes
                byte[] ipBytes = InetAddress.getByName(data).getAddress();
                buffer.put(ipBytes);
                break;
            case CNAME, NS, PTR, SOA, MX, TXT, SRV:
                // Convert domain name to bytes
                serializeName(buffer, data);
                break;
            // Add more cases for other types as needed
            default:
                throw new UnsupportedOperationException("Unsupported type: " + type);
        }
    }
}
