package org.example.config.changeConfig;

public class ArgsHandler {
    public static void handleArgs(String[] args) {
        if (args != null && args.length > 0) {
            // 创建ChangeConfigValues对象
            ChangeConfigValues configValues = new ChangeConfigValues();

            // 根据参数进行配置修改
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];

                // 根据不同参数标识进行处理
                if (arg.equals("-ip")) {
                    if (i + 1 < args.length) {
                        String ipValue = args[i + 1];
                        configValues.changeRootServerIP(ipValue);
                    }
                } else if (arg.equals("-rp")) {
                    if (i + 1 < args.length) {
                        String portValue = args[i + 1];
                        configValues.changeRootServerPort(portValue);
                    }
                } else if (arg.equals("-rl")) {
                    if (i + 1 < args.length) {
                        String relayValue = args[i + 1];
                        configValues.changeDNSRelay(relayValue);
                    }
                } else if (arg.equals("-lp")) {
                    if (i + 1 < args.length) {
                        String portValue = args[i + 1];
                        configValues.changeLocalDNSPort(portValue);
                    }
                } else if (arg.equals("-ed")) {
                    if (i + 1 < args.length) {
                        String debugValue = args[i + 1];
                        configValues.changeEnableDebug(debugValue);
                    }
                }
            }
        }
    }
}
