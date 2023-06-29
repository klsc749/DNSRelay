package org.example.config.changeConfig;
import org.example.config.SystemConfig;

public class InputValidator {
    private final String[] args;

    public InputValidator(String[] args) {
        this.args = args;
    }

    public boolean isValidInput() {
        if (isValidIpAddress(args[0]) && isValidPort(args[1]) && isValidFileName(args[2]) && isValidPort(args[3]) && isValidBoolean(args[4])) {
            return true;
        }
        return false;
    }

    // 校验IP地址是否合法
    private boolean isValidIpAddress(String ip) {
        String[] parts = ip.split("\\.");

        if (parts.length != 4) {
            return false;
        }

        for (String part : parts) {
            int num;
            try {
                num = Integer.parseInt(part);
            } catch (NumberFormatException e) {
                return false;
            }

            if (num < 0 || num > 255) {
                return false;
            }
        }

        return true;
    }

    // 校验端口号是否合法
    private boolean isValidPort(String port) {
        int num;
        try {
            num = Integer.parseInt(port);
        } catch (NumberFormatException e) {
            return false;
        }

        return num >= 1 && num <= 65535;
    }

    // 校验文件名是否合法
    private boolean isValidFileName(String fileName) {
        return fileName.endsWith(".txt");
    }

    // 校验布尔值是否合法
    private boolean isValidBoolean(String value) {
        return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false");
    }
}
