package com.lucke01.banwhendie;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.lucke01.banwhendie.core.ConfigFile;
import com.lucke01.banwhendie.database.Database;
import com.lucke01.banwhendie.lang.Messager;
import com.lucke01.banwhendie.listeners.PlayerListener;

public class BanWhenDiePlugin extends JavaPlugin {
	FileConfiguration config = getConfig();
	public Database database = null;
	
	private ConfigFile configFileObject;
	
	// Fired when plugin is first enabled
    @Override
    public void onEnable() {
        //setup configFileObject
        this.configFileObject = ConfigFile.createConfigFileObject(this.config);
        
        //DEBUG
        System.out.println("CONFIG FILE OBJECT: "+this.configFileObject);
        if (this.configFileObject.isActivateBanWhenDied()) {
            System.out.println(Messager.mainMessenger().getText("plugin.enabled"));
            
            saveResource("config.yml", false);
            saveResource("fallenPlayers.yml", false);
            
            //add listeners
            this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
            //DEBUG
            System.out.println("Data folder: "+this.getDataFolder().getAbsolutePath());
            
            //setup database
            this.database = Database.create(configFileObject.getDataBaseType());
            this.database.connect();
            
            //setup messager
            //this.messager = new Messager(this.configFileObject.getLang(), true);
            Messager.setMainMessenger(this.configFileObject.getLang(), true);
            //DEBUG
            System.out.println("messager: "+Messager.mainMessenger());
        }
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
        if (this.configFileObject.isActivateBanWhenDied()) {
            System.out.println(Messager.mainMessenger().getText("plugin.disabled"));
    	    this.database.disconnect();
        }
    }
    //getters and setters
    public ConfigFile getConfigFileObject() {
        return configFileObject;
    }
    public void setConfigFileObject(ConfigFile configFileObject) {
        this.configFileObject = configFileObject;
    }
}