package test.lang;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.lucke01.banwhendie.util.PropertiesUtil;

public class PropertiesLoadTest {
    @Test
    public void loadTest() {
        Map<String, String> esProp = PropertiesUtil.loadPropertieFile("/lang/en_US.properties");
        System.out.println(esProp);
        
    }
}
