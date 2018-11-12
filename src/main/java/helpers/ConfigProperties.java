package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.google.common.base.Strings;

public class ConfigProperties {

    private static Properties PROPERTIES = null;
    private static InputStream input = null;

    static {

        System.setProperty("log4j.configurationFile","./log4j2.properties");

        PROPERTIES = new Properties();
        try {
            input = new FileInputStream("./test.properties");
            PROPERTIES.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return Strings.nullToEmpty(PROPERTIES.getProperty(key, null));
    }
}