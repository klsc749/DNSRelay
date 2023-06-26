package org.example.dns.model;

public class Flag {
    private boolean qr;
    private int opcode;
    private boolean aa;
    private boolean tc;
    private boolean rd;
    private boolean ra;
    private int z;
    private int rcode;

    public Flag() {
    }

    public Flag(boolean qr, int opcode, boolean aa, boolean tc, boolean rd, boolean ra, int z, int rcode) {
        this.qr = qr;
        this.opcode = opcode;
        this.aa = aa;
        this.tc = tc;
        this.rd = rd;
        this.ra = ra;
        this.z = z;
        this.rcode = rcode;
    }

    public void setQr(boolean qr) {
        this.qr = qr;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public void setAa(boolean aa) {
        this.aa = aa;
    }

    public void setTc(boolean tc) {
        this.tc = tc;
    }

    public void setRd(boolean rd) {
        this.rd = rd;
    }

    public void setRa(boolean ra) {
        this.ra = ra;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setRcode(int rcode) {
        this.rcode = rcode;
    }

    public boolean isQr() {
        return qr;
    }

    public int getOpcode() {
        return opcode;
    }

    public boolean isAa() {
        return aa;
    }

    public boolean isTc() {
        return tc;
    }

    public boolean isRd() {
        return rd;
    }

    public boolean isRa() {
        return ra;
    }

    public int getZ() {
        return z;
    }

    public int getRcode() {
        return rcode;
    }

    @Override
    public String toString() {
        return "Flag{" +
                "qr=" + qr +
                ", opcode=" + opcode +
                ", aa=" + aa +
                ", tc=" + tc +
                ", rd=" + rd +
                ", ra=" + ra +
                ", z=" + z +
                ", rcode=" + rcode +
                '}';
    }
}
