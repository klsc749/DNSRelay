package org.example.dns.model;

public class Header {
    private int id;
    private Flag flags;
    private int qdCount;
    private int anCount;
    private int nsCount;
    private int arCount;

    public  Header(){

    }

    public Header(int id, Flag flags, int qdCount, int anCount, int nsCount, int arCount) {
        this.id = id;
        this.flags = flags;
        this.qdCount = qdCount;
        this.anCount = anCount;
        this.nsCount = nsCount;
        this.arCount = arCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFlags(Flag flags) {
        this.flags = flags;
    }

    public void setQdCount(int qdCount) {
        this.qdCount = qdCount;
    }

    public void setAnCount(int anCount) {
        this.anCount = anCount;
    }

    public void setNsCount(int nsCount) {
        this.nsCount = nsCount;
    }

    public void setArCount(int arCount) {
        this.arCount = arCount;
    }

    public int getId() {
        return id;
    }

    public Flag getFlags() {
        return flags;
    }

    public int getQdCount() {
        return qdCount;
    }

    public int getAnCount() {
        return anCount;
    }

    public int getNsCount() {
        return nsCount;
    }

    public int getArCount() {
        return arCount;
    }

    @Override
    public String toString() {
        return "Header{" +
                "id=" + id +
                ", flags=" + flags +
                ", qdCount=" + qdCount +
                ", anCount=" + anCount +
                ", nsCount=" + nsCount +
                ", arCount=" + arCount +
                '}';
    }
}
