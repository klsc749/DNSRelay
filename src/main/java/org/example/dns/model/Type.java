package org.example.dns.model;

public enum Type {
    A(1),
    NS(2),
    CNAME(5),
    SOA(6),
    PTR(12),
    MX(15),
    TXT(16),
    AAAA(28),
    SRV(33),
    UNKNOWN(-1);

    private final int value;

    Type(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Type fromValue(int value) {
        for (Type type : Type.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public static byte[] toBytes(Type type) {
        return new byte[] {(byte) (type.getValue() >> 8), (byte) type.getValue()};
    }

    public static Type fromBytes(byte[] bytes) {
        int value = ((bytes[0] & 0xFF) << 8) | (bytes[1] & 0xFF);
        return fromValue(value);
    }
}
