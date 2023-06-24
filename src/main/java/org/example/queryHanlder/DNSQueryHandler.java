package org.example.queryHanlder;

import org.example.queryHanlder.model.DNSRecord;

public interface DNSQueryHandler {
    DNSRecord handle(String domainName);
    void setNext(DNSQueryHandler next);
}
