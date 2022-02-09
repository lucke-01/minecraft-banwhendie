package com.lucke01.banwhendie.database;

import java.util.List;

import org.bukkit.entity.Player;

import com.lucke01.banwhendie.dto.FallenPlayer;
import com.lucke01.banwhendie.lang.Messager;

public abstract class Database {
    public Database() {
    }
    public static Database create(String type) {
        switch (type) {
            case "mysql":
            	//return new MySQL();
            case "sqlite":
                //return new SQLite();
            case "h2":
            default:
                System.out.println(Messager.mainMessenger().getText("database.invalidDB"));
            case "yml":
                return new YmlDB();
        }
    }
    public abstract void connect();
    public abstract void disconnect();
    
    public abstract void saveFallenPlayer(Player player);
    public abstract FallenPlayer findFallenPlayerByPlayer(Player player);
    public abstract List<FallenPlayer> findAllPlayers();
}