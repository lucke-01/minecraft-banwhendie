package com.lucke01.banwhendie.lang;

import java.util.Map;
import java.util.Objects;

public class LanguageMessager {
    private Language language;
    private Map<String, String> messageProperties;
    
    public LanguageMessager() {
        this(null,null);
    }
    public LanguageMessager(Language language) {
        this(language,null);
    }
    public LanguageMessager(Language language, Map<String, String> messageProperties) {
        super();
        this.language = language;
        this.messageProperties = messageProperties;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(language);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LanguageMessager other = (LanguageMessager) obj;
        return language == other.language;
    }
    
    @Override
    public String toString() {
        return "LanguageMessager [language=" + language + ", messageProperties=" + messageProperties + "]";
    }
    //getters and setters
    public Language getLanguage() {
        return language;
    }
    public void setLanguage(Language language) {
        this.language = language;
    }
    public Map<String, String> getMessageProperties() {
        return messageProperties;
    }
    public void setMessageProperties(Map<String, String> messageProperties) {
        this.messageProperties = messageProperties;
    }
}
