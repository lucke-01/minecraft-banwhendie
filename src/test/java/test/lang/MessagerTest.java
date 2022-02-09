package test.lang;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lucke01.banwhendie.lang.Language;
import com.lucke01.banwhendie.lang.Messager;

public class MessagerTest {
    
    private Messager messager = new Messager();
    
    @BeforeEach
    public void setUp() {
        messager.loadAllLanguagesProperties();
    }
    
    @Test
    public void loadTest() {
        System.out.println(messager);
        
        System.out.println(messager.getText("language"));
        System.out.println(messager.getTextByLanguage(Language.SPANISH,"language"));
    }
}