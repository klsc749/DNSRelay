package org.example.dns.model;

public class RRecord extends Section {
    private int ttl;
    private int dataLength;
    private String data;

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", classCode=" + classCode +
                ", ttl=" + ttl +
                ", dataLength=" + dataLength +
                ", data='" + data + '\'' +
                '}';
    }
}
