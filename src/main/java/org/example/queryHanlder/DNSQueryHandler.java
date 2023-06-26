package org.example.queryHanlder;

import org.example.dns.model.Message;
import org.example.dns.model.Question;

public interface DNSQueryHandler {
    Message handle(Question question);
    void setNext(DNSQueryHandler next);
}
