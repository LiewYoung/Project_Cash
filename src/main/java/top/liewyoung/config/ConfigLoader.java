package top.liewyoung.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private ConfigLoader() {
    }

    private final static String configPath = "src/main/Config/config.prop";

    public static String getConfigPath() {
        return configPath;
    }

    public static String getValue(String key) {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(configPath));
            return prop.getProperty(key);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
