package org.example.queryHanlder.remoteServerHandler;

import org.example.queryHanlder.DNSQueryHandler;
import org.example.queryHanlder.model.DNSRecord;

public class RemoteServerHandler implements DNSQueryHandler {
    private DNSQueryHandler next;

    @Override
    public void setNext(DNSQueryHandler next) {
        this.next = next;
    }

    @Override
    public DNSRecord handle(String domainName) {
        // 实现查询远程DNS服务器的逻辑...
        // 如果查询到结果，返回
        // 否则，调用下一个处理器的handle方法
        return null;
    }
}
