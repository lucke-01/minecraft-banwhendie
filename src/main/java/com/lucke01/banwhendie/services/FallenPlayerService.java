package com.lucke01.banwhendie.services;

import java.time.LocalDateTime;

import org.bukkit.entity.Player;

import com.lucke01.banwhendie.BanWhenDiePlugin;
import com.lucke01.banwhendie.dto.FallenPlayer;

public class FallenPlayerService {
    
    public BanWhenDiePlugin plugin;
    
    public FallenPlayerService() {
        try {
            plugin = BanWhenDiePlugin.getPlugin(BanWhenDiePlugin.class);
        } catch (Exception e) {
            
        }
    }
    
    public FallenPlayer getFallenPlayerFromPlayer(Player player) {
        LocalDateTime now = LocalDateTime.now();
        FallenPlayer fallenPlayer = new FallenPlayer();
        
        if (plugin.getConfigFileObject().isBannedByIp() == true) {
            fallenPlayer.setIp(player.getAddress().getHostString());
        }
        if (plugin.getConfigFileObject().isBannedByNickName() == true) {
            fallenPlayer.setNickname(player.getName());
        }
        
        fallenPlayer.setBanTime(plugin.getConfigFileObject().getBantime());
        fallenPlayer.setFallenDate(now);
        LocalDateTime releaseDate = this.calculateReleaseDate(now, plugin.getConfigFileObject().getBantime());
        fallenPlayer.setReleaseDate(releaseDate);
        
        return fallenPlayer;
    }
    
    public LocalDateTime calculateReleaseDate(LocalDateTime dateFallen, String banTime) {
        if (dateFallen == null) {
            return null;
        }
        if (banTime == null || banTime.isEmpty()) {
            return dateFallen;
        }
        long secondsBan = convertBanTimeInSeconds(banTime);
        return dateFallen.plusSeconds(secondsBan);
    }
    public long convertBanTimeInSeconds(String banTime) {
        long banTimeMiliseconds = 0;
        if (banTime != null && !banTime.isEmpty()) {
            //S = seconds, M = minutes, H = hours, D = days
            String unitTime = banTime.substring(banTime.length() - 1);
            long time = Integer.parseInt(banTime.substring(0, banTime.length() - 1));
            int multiplier = 1;
            switch (unitTime) {
                case "m":
                case "M":
                    multiplier = 60;
                    break;
                case "h":
                case "H":
                    multiplier = 60 * 60;
                    break;
                case "d":
                case "D":
                    multiplier = 60 * 60 * 24;
                    break;
            }
            banTimeMiliseconds = time * multiplier;
        }
        return banTimeMiliseconds;
    }
}
