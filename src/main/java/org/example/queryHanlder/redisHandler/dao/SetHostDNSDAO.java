package org.example.queryHanlder.redisHandler.dao;

import org.example.pool.redisConnectionPool.Callable;
import redis.clients.jedis.Jedis;

public class SetHostDNSDAO implements Callable {
    private String key;
    private String value;
    private long ttl;

    public SetHostDNSDAO() {
    }

    @Override
    public void call(Jedis jedis) {
        jedis.setex(key, ttl, value);
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }
}
