package com.lucke01.banwhendie.core;

import org.bukkit.configuration.file.FileConfiguration;

import com.lucke01.banwhendie.lang.Language;

public class ConfigFile {

    public static final String KEY_ACTIVATE_BAN_WHEN_DIED = "activateBanWhenDied";
    public static final String KEY_BANTIME = "bantime";
    public static final String KEY_BANNED_BY_IP = "bannedByIp";
    public static final String KEY_BANNED_BY_NICKNAME = "bannedByNickname";
    public static final String KEY_DATABASE_TYPE = "databaseType";
    public static final String KEY_LANG = "lang";
    public static final String KEY_PREVENT_ITEM_DESPAWN = "preventItemDespawn";
    
    private boolean activateBanWhenDied;
    private String bantime;
    private boolean bannedByIp;
    private boolean bannedByNickName;
    private String dataBaseType;
    private Language lang;
    private boolean preventItemDespawn;
    
    
    public static ConfigFile createConfigFileObject(FileConfiguration config) {
        ConfigFile configFile = new ConfigFile();
        
        configFile.setActivateBanWhenDied(config.get(KEY_ACTIVATE_BAN_WHEN_DIED) != null ? (Boolean)config.get(KEY_ACTIVATE_BAN_WHEN_DIED) : false);
        configFile.setBantime((String)config.get(KEY_BANTIME,"0M"));
        configFile.setBannedByIp(config.get(KEY_BANNED_BY_IP) != null ? (Boolean)config.get(KEY_BANNED_BY_IP) : false);
        configFile.setBannedByNickName(config.get(KEY_BANNED_BY_NICKNAME) != null ? (Boolean)config.get(KEY_BANNED_BY_NICKNAME) : false);
        configFile.setDataBaseType((String)config.get(KEY_DATABASE_TYPE,"yml"));
        
        String lang = (String)config.get(KEY_LANG,"en_US");
        Language language = Language.getFindingShortCode(lang);
        
        configFile.setLang(language);
        
        configFile.setPreventItemDespawn(config.get(KEY_PREVENT_ITEM_DESPAWN) != null ? (Boolean)config.get(KEY_PREVENT_ITEM_DESPAWN) : false);
        
        return configFile;
    }
        
    @Override
    public String toString() {
        return "ConfigFile [activateBanWhenDied=" + activateBanWhenDied + ", bantime=" + bantime + ", bannedByIp="
                + bannedByIp + ", bannedByNickName=" + bannedByNickName + ", dataBaseType=" + dataBaseType + ", lang="
                + lang + "]";
    }

    //getters and setters
    public boolean isActivateBanWhenDied() {
        return activateBanWhenDied;
    }
    public void setActivateBanWhenDied(boolean activateBanWhenDied) {
        this.activateBanWhenDied = activateBanWhenDied;
    }
    public String getBantime() {
        return bantime;
    }
    public void setBantime(String bantime) {
        this.bantime = bantime;
    }
    public boolean isBannedByIp() {
        return bannedByIp;
    }
    public void setBannedByIp(boolean bannedByIp) {
        this.bannedByIp = bannedByIp;
    }
    public boolean isBannedByNickName() {
        return bannedByNickName;
    }
    public void setBannedByNickName(boolean bannedByNickName) {
        this.bannedByNickName = bannedByNickName;
    }
    public String getDataBaseType() {
        return dataBaseType;
    }
    public void setDataBaseType(String dataBaseType) {
        this.dataBaseType = dataBaseType;
    }
    public Language getLang() {
        return lang;
    }
    public void setLang(Language lang) {
        this.lang = lang;
    }
    public boolean isPreventItemDespawn() {
        return preventItemDespawn;
    }
    public void setPreventItemDespawn(boolean preventItemDespawn) {
        this.preventItemDespawn = preventItemDespawn;
    }
}
