package org.example.queryHanlder.localHostHanlder;

import org.example.config.SystemConfig;
import org.example.dns.model.*;
import org.example.queryHanlder.DNSQueryHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class LocalHostHandler implements DNSQueryHandler {
    private DNSQueryHandler next;
    private ConcurrentHashMap<String, String> localHostMap;

    public LocalHostHandler() {
        this.localHostMap = new ConcurrentHashMap<>();
        try {
            // 读取文件中的所有行
            List<String> lines = Files.readAllLines(Path.of(SystemConfig.DNS_RELAY));

            // 遍历每一行，将其添加到ConcurrentHashMap中
            for (String line : lines) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    this.localHostMap.put(parts[0]+".", parts[1]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Message handle(Question question) {
        if(SystemConfig.ENABLE_DEBUG){
            System.out.println(Thread.currentThread().getName() + " LocalHostHandler handle " + question.getName());
        }
        String s = localHostMap.get(question.getName());

        // 从localHostMap中查询
        if(s == null){
            return next == null ? null : next.handle(question);
        }

        Message message = new Message();
        Header header = new Header();
        header.setFlags(new Flag());
        header.getFlags().setQr(true);
        header.setQdCount(1);
        message.setHeader(header);

        List<Section> sections = new ArrayList<>(2);
        message.setSections(sections);
        sections.add(question);

        if("0.0.0.0".equals(s)){
            header.getFlags().setRcode(3);
            header.setAnCount(0);
        }else {
            header.setAnCount(1);
            RRecord rRecord = new RRecord();
            rRecord.setName(question.getName());
            rRecord.setType(question.getType());
            rRecord.setClassCode(1);
            rRecord.setTtl(100);
            rRecord.setDataLength(4);
            rRecord.setData(s);
            sections.add(rRecord);
        }

        if(SystemConfig.ENABLE_DEBUG){
            System.out.println(Thread.currentThread().getName() + " LocalHostHandler handle " + question.getName() + " result " + s);
        }

        return message;
    }

    @Override
    public void setNext(DNSQueryHandler next) {
        this.next = next;
    }
}
