package top.liewyoung.config;

import top.liewyoung.config.Exceptions.KEYNULLException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author LiewYoung
 * @since 2025/12/6
 */

public class ConfigBuilder {
    private static String apiKey;
    private static String model = "deepseek-chat";
    private static int count = 0;

    private ConfigBuilder() {
    }

    public static void put(String apiKey, String model) {
        ConfigBuilder.apiKey = apiKey;
        ConfigBuilder.model = model;
    }

    public static void put(String apiKey) {
        ConfigBuilder.apiKey = apiKey;
    }

    public static void generate() {
        try {
            if (ConfigBuilder.apiKey == null) {
                throw new KEYNULLException();
            }
            Properties prop = new Properties();
            prop.put("apiKey", apiKey);
            prop.put("model", model);

            try (FileOutputStream fos = new FileOutputStream("src/main/Config/config.prop")) {
                prop.store(fos, "SET " + (++count) + " Times");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (KEYNULLException e) {
            System.out.println(e.getMessage());
        }
    }
}
