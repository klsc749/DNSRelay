package org.example.dns.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeTest {
    @Test
    public void testGetValue() {
        assertEquals(1, Type.A.getValue());
        assertEquals(2, Type.NS.getValue());
        assertEquals(5, Type.CNAME.getValue());
    }

    @Test
    public void testFromValue() {
        assertEquals(Type.A, Type.fromValue(1));
        assertEquals(Type.NS, Type.fromValue(2));
        assertEquals(Type.CNAME, Type.fromValue(5));
        assertEquals(Type.UNKNOWN, Type.fromValue(0));
    }

    @Test
    public void testToBytes() {
        assertArrayEquals(new byte[]{0, 1}, Type.toBytes(Type.A));
        assertArrayEquals(new byte[]{0, 2}, Type.toBytes(Type.NS));
        assertArrayEquals(new byte[]{0, 5}, Type.toBytes(Type.CNAME));
    }

    @Test
    public void testFromBytes() {
        assertEquals(Type.A, Type.fromBytes(new byte[]{0, 1}));
        assertEquals(Type.NS, Type.fromBytes(new byte[]{0, 2}));
        assertEquals(Type.CNAME, Type.fromBytes(new byte[]{0, 5}));
        assertEquals(Type.UNKNOWN, Type.fromBytes(new byte[]{0, 0}));
    }
}
