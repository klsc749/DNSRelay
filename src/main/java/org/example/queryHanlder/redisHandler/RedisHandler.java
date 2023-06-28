package org.example.queryHanlder.redisHandler;

import org.example.dns.model.*;
import org.example.queryHanlder.DNSQueryHandler;
import org.example.queryHanlder.redisHandler.model.ValueWithTTL;
import org.example.queryHanlder.redisHandler.service.RedisService;

import java.util.ArrayList;
import java.util.List;

public class RedisHandler implements DNSQueryHandler {
    private DNSQueryHandler next;

    private final RedisService redisService;

    public RedisHandler(RedisService redisService){
        this.redisService = redisService;
    }


    @Override
    public Message handle(Question question) {
        String host = question.getName();
        ValueWithTTL result = redisService.getHostDNS(host);
        if(result != null && result.getValue() != null && result.getTtl() > 0){
            Message message = new Message();
            Header header = new Header();
            header.setFlags(new Flag());
            header.getFlags().setQr(true);
            header.setQdCount(1);
            message.setHeader(header);

            List<Section> sections = new ArrayList<>(2);
            message.setSections(sections);
            sections.add(question);

            header.setAnCount(1);
            RRecord rRecord = new RRecord();
            rRecord.setName(question.getName());
            rRecord.setType(question.getType());
            rRecord.setTtl((int)result.getTtl());
            rRecord.setDataLength(4);
            rRecord.setData(result.getValue());
            sections.add(rRecord);
            return message;
        }
        return next == null ? null : next.handle(question);
    }

    @Override
    public void setNext(DNSQueryHandler next) {
        this.next = next;
    }
}
