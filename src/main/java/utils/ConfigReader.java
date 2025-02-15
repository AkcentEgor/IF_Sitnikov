package utils;

import com.codeborne.selenide.Config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try {
            InputStream input = Config.class.getResourceAsStream("/config.properties");
            properties.load(input);
        }
        catch (Exception e) {
            throw new RuntimeException("Не удалось загрузить файл конфигурации");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
