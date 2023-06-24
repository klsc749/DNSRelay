package org.example.queryHanlder.RedisHandler;

import org.example.queryHanlder.DNSQueryHandler;
import org.example.queryHanlder.model.DNSRecord;

public class RedisHandler implements DNSQueryHandler {
    private DNSQueryHandler next;

    @Override
    public DNSRecord handle(String domainName) {
        // 实现查询Redis的逻辑...
        // 如果Redis中有对应的记录，返回结果
        // 否则，调用下一个处理器的handle方法
        return next != null ? next.handle(domainName) : null;
    }

    @Override
    public void setNext(DNSQueryHandler next) {
        this.next = next;
    }
}
