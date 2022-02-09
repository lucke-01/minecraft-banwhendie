package com.lucke01.banwhendie.lang;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;

import com.lucke01.banwhendie.util.PropertiesUtil;

public class Messager {
    private LanguageMessager mainLanguageMessager;
    
    private Set<LanguageMessager> setLanguageMessager = new HashSet<>();
    
    private static Messager mainMessenger;
    
    public Messager() {
        this(Language.ENGLISH);
    }
    public Messager(Language language) {
        this(language,false);
    }
    public Messager(Language language, boolean autoload) {
        Map<String, String> messageEnProperties = PropertiesUtil.loadPropertieFile("/lang/"+language.getShortCode()+".properties");
        this.mainLanguageMessager = new LanguageMessager(language, messageEnProperties);
        if (autoload) {
            this.loadAllLanguagesProperties();
        }
    }
    public void loadAllLanguagesProperties() {
        for (Language language : Language.values()) {
            Map<String, String> messageProperties = PropertiesUtil.loadPropertieFile("/lang/"+language.getShortCode()+".properties");
            LanguageMessager languageMessager = new LanguageMessager(language ,messageProperties);
            setLanguageMessager.add(languageMessager);
        }
    }
    
    public String getText(String keyText) {
        return mainLanguageMessager.getMessageProperties().get(keyText);
    }
    public String getTextByLanguage(Language language, String keyText) {
        LanguageMessager languageMessager = setLanguageMessager.stream().filter(item->item.equals(new LanguageMessager(language))).findFirst().orElse(mainLanguageMessager);
        return languageMessager.getMessageProperties().get(keyText);
    }
    public String getTextByPlayer(Player player, String keyText) {
        Language languagePlayer = Language.getLanguage(player);
        
        LanguageMessager languageMessager = setLanguageMessager.stream().filter(item->item.equals(new LanguageMessager(languagePlayer))).findFirst().orElse(mainLanguageMessager);
        
        String messageText = languageMessager.getMessageProperties().get(keyText);
        
        return messageText;
    }
    
    public static Messager setMainMessenger(Language language, boolean autoload) {
        if (mainMessenger == null) {
            mainMessenger = new Messager(language, autoload);
        }
        return mainMessenger;
    }
    public static Messager mainMessenger() {
        if (mainMessenger == null) {
            mainMessenger = new Messager(Language.ENGLISH, true);
        }
        return mainMessenger;
    }

    @Override
    public String toString() {
        return "Messager [mainLanguageMessager=" + mainLanguageMessager + ", setLanguageMessager=" + setLanguageMessager
                + "]";
    }
}