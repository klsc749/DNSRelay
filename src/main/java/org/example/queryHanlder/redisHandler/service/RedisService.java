package org.example.queryHanlder.redisHandler.service;

import org.example.pool.redisConnectionPool.RedisConnectionPool;
import org.example.queryHanlder.redisHandler.dao.GetHostDNSDao;
import org.example.queryHanlder.redisHandler.dao.SetHostDNSDAO;
import org.example.queryHanlder.redisHandler.model.ValueWithTTL;
import redis.clients.jedis.JedisPool;

public class RedisService {
    private RedisConnectionPool redisConnectionPool;

    public RedisService(RedisConnectionPool redisConnectionPool) {
        this.redisConnectionPool = redisConnectionPool;
    }

    public void setHostDNS(String host, String ip, long ttl){
        SetHostDNSDAO setHostDNSDAO = new SetHostDNSDAO();
        setHostDNSDAO.setKey(host);
        setHostDNSDAO.setValue(ip);
        setHostDNSDAO.setTtl(ttl);
        redisConnectionPool.executeStringCallable(setHostDNSDAO);
    }

    public ValueWithTTL getHostDNS(String host){
        GetHostDNSDao getHostDNSDao = new GetHostDNSDao();
        getHostDNSDao.setKey(host);
        redisConnectionPool.executeStringCallable(getHostDNSDao);
        return getHostDNSDao.getResult();
    }
}
