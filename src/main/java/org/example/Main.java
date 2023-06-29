package org.example;

import org.example.config.changeConfig.ChangeConfigValues;
import org.example.config.changeConfig.InputValidator;
import org.example.dns.DNSRelay;
import java.io.IOException;
import org.example.config.SystemConfig;

public class Main {
    public static void main(String[] args) throws IOException {
        // 创建ChangeConfigValues对象
        ChangeConfigValues changeConfigValues = new ChangeConfigValues();
        if (args.length >= 5) {
            InputValidator inputValidator = new InputValidator(args);
            if (inputValidator.isValidInput()) {
                changeConfigValues.Change(args);
            }
        }

        // 打印变量值
        /*
        System.out.println("ROOT_SERVER_IP值：" + SystemConfig.ROOT_SERVER_IP);
        System.out.println("ROOT_SERVER_PORT值：" + SystemConfig.ROOT_SERVER_PORT);
        System.out.println("DNS_RELAY值：" + SystemConfig.DNS_RELAY);
        System.out.println("LOCAL_DNS_PORT值：" + SystemConfig.LOCAL_DNS_PORT);
        System.out.println("ENABLE_DEBUG值：" + SystemConfig.ENABLE_DEBUG);
        */

        //DNSRelay dnsRelay = new DNSRelay();
        //dnsRelay.start();
    }
}