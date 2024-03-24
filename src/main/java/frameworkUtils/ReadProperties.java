package frameworkUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
    private static ReadProperties instance = null;

    private static String propertiesFile = "src/main/java/configuration/general.properties";
    private Properties properties = null;

    public ReadProperties() throws IOException {
        properties = new Properties();
            properties.load(new FileReader(propertiesFile));

    }

    public String getValue(String key) {
        return properties.getProperty(key, String.format("The key %s does not exists!", key));
    }

}
