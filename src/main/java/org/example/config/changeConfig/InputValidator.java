package org.example.config.changeConfig;
import org.example.config.SystemConfig;

public class InputValidator {
    public static boolean validateIP(String ipValue) {
        // 验证IPv4地址格式
        String ipPattern = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$";
        return ipValue.matches(ipPattern);
    }

    public static boolean validatePort(String portValue) {
        // 验证端口范围
        int port = Integer.parseInt(portValue);
        return port >= 1 && port <= 65535;
    }

    public static boolean validateDNSRelay(String relayValue) {
        // 验证文件名后缀为.txt
        return relayValue.endsWith(".txt");
    }

    public static boolean validateBoolean(String boolValue) {
        // 验证布尔值
        return boolValue.equalsIgnoreCase("true") || boolValue.equalsIgnoreCase("false");
    }
}
