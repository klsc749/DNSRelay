package org.example.queryHanlder.localHostHanlder;

import org.example.queryHanlder.DNSQueryHandler;
import org.example.queryHanlder.model.DNSRecord;

public class LocalHostHandler implements DNSQueryHandler {
    private DNSQueryHandler next;
    @Override
    public DNSRecord handle(String domainName) {
        //TODO: implement
        return null;
    }

    @Override
    public void setNext(DNSQueryHandler next) {
        this.next = next;
    }
}
