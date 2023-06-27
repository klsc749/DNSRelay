package org.example;

import org.example.dns.DNSRelay;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DNSRelay dnsRelay = new DNSRelay();
        dnsRelay.start();
    }
}