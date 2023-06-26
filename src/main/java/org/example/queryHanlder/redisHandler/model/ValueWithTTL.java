package org.example.queryHanlder.redisHandler.model;

public class ValueWithTTL {
    private String value;
    private long ttl;

    public ValueWithTTL(String value, long ttl) {
        this.value = value;
        this.ttl = ttl;
    }

    public String getValue() {
        return value;
    }

    public long getTtl() {
        return ttl;
    }

    @Override
    public String toString() {
        return "ValueWithTTL{" +
                "value='" + value + '\'' +
                ", ttl=" + ttl +
                '}';
    }
}
