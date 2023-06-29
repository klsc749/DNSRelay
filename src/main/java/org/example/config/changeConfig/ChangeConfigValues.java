package org.example.config.changeConfig;

import org.example.config.SystemConfig;

public class ChangeConfigValues implements ChangeConfig {
    public void Change(String[] args) {
        if (args.length >= 4) {
            SystemConfig.ROOT_SERVER_IP = args[0];
            SystemConfig.ROOT_SERVER_PORT = args[1];
            SystemConfig.DNS_RELAY = args[2];
            SystemConfig.LOCAL_DNS_PORT = Integer.parseInt(args[3]);
        }

        if (args.length >= 5) {
            SystemConfig.ENABLE_DEBUG = Boolean.parseBoolean(args[4]);
        }
    }
}
