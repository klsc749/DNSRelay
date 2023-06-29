package org.example.config.changeConfig;

import org.example.config.SystemConfig;

public class ChangeConfigValues{
    public void changeRootServerIP(String ipValue) {
        if (InputValidator.validateIP(ipValue)) {
            SystemConfig.ROOT_SERVER_IP = ipValue;
        } else {
            System.out.println("无效的ROOT_SERVER_IP值！");
        }
    }

    public void changeRootServerPort(String portValue) {
        if (InputValidator.validatePort(portValue)) {
            SystemConfig.ROOT_SERVER_PORT = portValue;
        } else {
            System.out.println("无效的ROOT_SERVER_PORT值！");
        }
    }

    public void changeDNSRelay(String relayValue) {
        if (InputValidator.validateDNSRelay(relayValue)) {
            SystemConfig.DNS_RELAY = relayValue;
        } else {
            System.out.println("无效的DNS_RELAY值！");
        }
    }

    public void changeLocalDNSPort(String portValue) {
        if (InputValidator.validatePort(portValue)) {
            SystemConfig.LOCAL_DNS_PORT = Integer.parseInt(portValue);
        } else {
            System.out.println("无效的LOCAL_DNS_PORT值！");
        }
    }

    public void changeEnableDebug(String debugValue) {
        if (InputValidator.validateBoolean(debugValue)) {
            SystemConfig.ENABLE_DEBUG = Boolean.parseBoolean(debugValue);
        } else {
            System.out.println("无效的ENABLE_DEBUG值！");
        }
    }

}
