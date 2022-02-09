package com.lucke01.banwhendie.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
    public static Properties getPropertiesFromResourcePath(String resourcePath) {
        Properties prop = new Properties();
        try (InputStream inputStream = PropertiesUtil.class.getResourceAsStream(resourcePath)) {
            prop.load(inputStream);
            
            return prop;
        } catch (Exception e) {
           return null;
        }
    }
    public static Map<String, String> loadPropertieFile(String resourcePath) {
        Map<String, String> mapProperties = new HashMap<>();
        Properties prop = getPropertiesFromResourcePath(resourcePath);
        for (String key : prop.stringPropertyNames()) {
            mapProperties.put(key, prop.getProperty(key));
        }
        return mapProperties;
    }
}
