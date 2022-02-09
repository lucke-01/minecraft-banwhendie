package com.lucke01.banwhendie.database;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import com.lucke01.banwhendie.BanWhenDiePlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class CustomConfig {

    private final Plugin plugin = BanWhenDiePlugin.getPlugin(BanWhenDiePlugin.class);
    private final String path;
    private final File file;
    private FileConfiguration config;

    public CustomConfig(String path) {
        this(path, true);
    }
    public CustomConfig(String path, boolean autoLoad) {
        this.path = path;
        this.file = new File(plugin.getDataFolder(), path);

        if (autoLoad) {
            saveDefault();
            reload();
        }
    }
    public void saveDefault() {
        if (file.exists()) return;

        plugin.getDataFolder().mkdir();

        try (InputStream in = plugin.getResource(path)) {
            if (in != null) {
                Files.copy(in, file.toPath());
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void reload() {
        try {
            config = YamlConfiguration.loadConfiguration(file);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
    public FileConfiguration get() {
        if (config == null) reload();
        return config;
    }
    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //getters and setters
	public FileConfiguration getConfig() {
		return config;
	}
	public String getPath() {
		return path;
	}
	public File getFile() {
		return file;
	}
}