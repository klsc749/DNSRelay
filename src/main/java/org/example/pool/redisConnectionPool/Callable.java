package org.example.pool.redisConnectionPool;

import redis.clients.jedis.Jedis;

public interface Callable {
    void call(Jedis jedis);
}
