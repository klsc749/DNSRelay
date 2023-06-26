package org.example.dns.resovler;

import org.example.dns.model.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DNSMessageParserI implements DNSMessageParser{
    public Message parse(byte[] data) {
        // 使用ByteBuffer来辅助处理字节数据
        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.order(ByteOrder.BIG_ENDIAN); // DNS协议是使用大端序

        // 解析Header
        Header header = parseHeader(buffer);

        List<Section> records = new ArrayList<>();
        // 解析Question
        for (int i = 0; i < header.getQdCount(); i++) {
            records.add(parseQuestion(buffer));
        }
        // 解析Answer
        for (int i = 0; i < header.getAnCount(); i++) {
            records.add(parseRecord(buffer));
        }
        // 解析Authority
        for (int i = 0; i < header.getNsCount(); i++) {
            records.add(parseRecord(buffer));
        }
        // 解析Additional
        for (int i = 0; i < header.getArCount(); i++) {
            records.add(parseRecord(buffer));
        }

        return new Message(header, records);
    }

    private Header parseHeader(ByteBuffer buffer) {
        Header header = new Header();

        header.setId(buffer.getShort() & 0xFFFF);
        int flags = buffer.getShort() & 0xFFFF;
        Flag flag = new Flag();
        flag.setQr((flags & 0x8000) != 0);
        flag.setOpcode((flags & 0x7800) >> 11);
        flag.setAa((flags & 0x0400) != 0);
        flag.setTc((flags & 0x0200) != 0);
        flag.setRd((flags & 0x0100) != 0);
        flag.setRa((flags & 0x0080) != 0);
        flag.setZ((flags & 0x0070) >> 4);
        flag.setRcode(flags & 0x000F);
        header.setFlags(flag);
        header.setQdCount(buffer.getShort() & 0xFFFF);
        header.setAnCount(buffer.getShort() & 0xFFFF);
        header.setNsCount(buffer.getShort() & 0xFFFF);
        header.setArCount(buffer.getShort() & 0xFFFF);

        return header;
    }

    public Question parseQuestion(ByteBuffer buffer) {
        Question question = new Question();
        question.setName(parseName(buffer));
        question.setType(Type.fromValue(buffer.getShort()));
        question.setClassCode(buffer.getShort());
        return question;
    }

    public RRecord parseRecord(ByteBuffer buffer) {
        RRecord record = new RRecord();
        record.setName(parseName(buffer));
        record.setType(Type.fromValue(buffer.getShort()));
        record.setClassCode(buffer.getShort());
        record.setTtl(buffer.getInt());
        record.setDataLength(buffer.getShort());
        record.setData(parseData(buffer, record.getType(), record.getDataLength()));
        return record;
    }

    // 这个方法用来从buffer中解析域名
    private String parseName(ByteBuffer buffer) {
        StringBuilder nameBuilder = new StringBuilder();
        int lenOrPointer;
        while ((lenOrPointer = buffer.get() & 0xFF) != 0) {
            if (lenOrPointer >= 192) { // This is a pointer
                int offset = ((lenOrPointer & 0x3F) << 8) | (buffer.get() & 0xFF);
                int oldPosition = buffer.position();
                buffer.position(offset);
                nameBuilder.append(parseName(buffer));
                buffer.position(oldPosition);
                break;
            } else { // This is a length
                for (int i = 0; i < lenOrPointer; i++) {
                    nameBuilder.append((char) buffer.get());
                }
                nameBuilder.append('.');
            }
        }
        return nameBuilder.toString();
    }

    // 这个方法用来从buffer中解析数据
    private String parseData(ByteBuffer buffer, Type type, int dataLength) {
        switch (type) {
            case A:
                byte[] ipBytes = new byte[4];
                buffer.get(ipBytes);
                try {
                    return InetAddress.getByAddress(ipBytes).getHostAddress();
                } catch (UnknownHostException e) {
                    throw new RuntimeException(e);
                }
            case CNAME:
                return parseName(buffer);
            default:
                byte[] dataBytes = new byte[dataLength];
                buffer.get(dataBytes);
                return new String(dataBytes, StandardCharsets.UTF_8);
        }
    }
}
