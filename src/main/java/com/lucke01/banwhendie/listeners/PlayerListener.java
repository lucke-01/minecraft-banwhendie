package com.lucke01.banwhendie.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.lucke01.banwhendie.BanWhenDiePlugin;
import com.lucke01.banwhendie.dto.FallenPlayer;
import com.lucke01.banwhendie.lang.Language;
import com.lucke01.banwhendie.lang.Messager;
import com.lucke01.banwhendie.util.DateUtility;

import java.time.LocalDateTime;

public class PlayerListener implements Listener {
	public BanWhenDiePlugin plugin = BanWhenDiePlugin.getPlugin(BanWhenDiePlugin.class);
	
	@EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        player.sendMessage("Welcome");
        
        //DEBUG
        System.out.println("CLIENT LANGUAGE: "+Language.getLanguageString(player));
        
        FallenPlayer fallenPlayeer = this.plugin.database.findFallenPlayerByPlayer(player);
        if (fallenPlayeer != null) {
            //DEBUG
            System.out.println("fallenPlayeer");
            System.out.println(fallenPlayeer);
            System.out.println("now: "+DateUtility.formatLocalDateTime(LocalDateTime.now()));
            System.out.println("release date: "+DateUtility.formatLocalDateTime(fallenPlayeer.getReleaseDate()));
            System.out.println("now compareTo release date: "+LocalDateTime.now().compareTo(fallenPlayeer.getReleaseDate()));
            //END DEBUG
            if (fallenPlayeer.getReleaseDate() != null && LocalDateTime.now().compareTo(fallenPlayeer.getReleaseDate()) < 0) {
                player.kickPlayer(Messager.mainMessenger().getText("playerlistener.onJoin.message")
                            +DateUtility.formatLocalDateTime(fallenPlayeer.getReleaseDate()));
            }
        } else {
            //DEBUG
            System.out.println("fallenPlayeer not found");
        }
        //DEBUG
        System.out.println("Players: "+this.plugin.database.findAllPlayers());
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        final Player player = event.getEntity();      
        final Player killer = event.getEntity().getKiller();
        if (player != null) {
        	player.sendMessage(Messager.mainMessenger().getText("playerlistener.onFall.message")+plugin.getConfigFileObject().getBantime());
        	System.out.println("TODO: save/modify here a file with players fallen: "+plugin.getDataFolder().getAbsolutePath());
        	this.plugin.database.saveFallenPlayer(player);
        	player.kickPlayer(Messager.mainMessenger().getText("playerlistener.onFall.message")+plugin.getConfigFileObject().getBantime());
        }
        //DEBUG
        System.out.println("Players: "+this.plugin.database.findAllPlayers());
    }
    
    /**
     * Preven item despawn if it is configured
     * @param e
     */
    @EventHandler
    public void onItemDespawn(final ItemDespawnEvent e) {
        if (plugin.getConfigFileObject().isPreventItemDespawn()) {
            e.setCancelled(true);
        }
    }
}
