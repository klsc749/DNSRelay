package org.example;

import org.example.config.changeConfig.ArgsHandler;
import org.example.config.changeConfig.ChangeConfigValues;
import org.example.config.changeConfig.InputValidator;
import org.example.dns.DNSRelay;
import java.io.IOException;
import org.example.config.SystemConfig;

public class Main {
    public static void main(String[] args) throws IOException {
        ArgsHandler.handleArgs(args);
        System.out.println("ROOT_SERVER_IP值：" + SystemConfig.ROOT_SERVER_IP);
        System.out.println("ROOT_SERVER_PORT值：" + SystemConfig.ROOT_SERVER_PORT);
        System.out.println("DNS_RELAY值：" + SystemConfig.DNS_RELAY);
        System.out.println("LOCAL_DNS_PORT值：" + SystemConfig.LOCAL_DNS_PORT);
        System.out.println("ENABLE_DEBUG值：" + SystemConfig.ENABLE_DEBUG);
        
        // 启动DNSRelay
        DNSRelay dnsRelay = new DNSRelay();
        dnsRelay.start();
    }
}