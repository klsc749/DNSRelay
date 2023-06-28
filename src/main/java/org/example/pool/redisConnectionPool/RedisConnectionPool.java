package org.example.pool.redisConnectionPool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisConnectionPool {
    private final JedisPool jedisPool;
    //实例化连接池
    public RedisConnectionPool() {
        this.jedisPool = new JedisPool();
    }
    //获取Redis连接资源，并确保在使用后归还
    public void executeStringCallable(Callable caller){
        try(Jedis jedis = jedisPool.getResource()){
            jedis.select(0);
            caller.call(jedis);
        }
    }
}
