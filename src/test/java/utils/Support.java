package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Support {

    public static Properties loadConfig() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");

        prop.load(fis);
        return prop;
    }

}
