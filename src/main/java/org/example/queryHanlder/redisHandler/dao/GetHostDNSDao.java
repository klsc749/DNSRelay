package org.example.queryHanlder.redisHandler.dao;

import org.example.pool.redisConnectionPool.Callable;
import org.example.queryHanlder.redisHandler.model.ValueWithTTL;
import redis.clients.jedis.Jedis;

public class GetHostDNSDao implements Callable {
    private String key;
    private ValueWithTTL result;

    public GetHostDNSDao() {
    }


    @Override
    public void call(Jedis jedis) {
        result = new ValueWithTTL(jedis.get(key), jedis.ttl(key));
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ValueWithTTL getResult() {
        return result;
    }
}
