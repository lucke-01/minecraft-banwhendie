package com.lucke01.banwhendie.database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

import com.lucke01.banwhendie.BanWhenDiePlugin;
import com.lucke01.banwhendie.dto.FallenPlayer;
import com.lucke01.banwhendie.services.FallenPlayerService;
import com.lucke01.banwhendie.util.CollectionsUtil;
import com.lucke01.banwhendie.util.DateUtility;
/**
 * Save and manage a list in-memory of players which periodically will be save into the file: /plugin/banwhendie/fallenPlayers.yml
 * in order to save performance instead of saving the file each time one player dies this will use in-memory list
 * @author ricardo
 *
 */
public class YmlDB extends Database {
    public BanWhenDiePlugin plugin = BanWhenDiePlugin.getPlugin(BanWhenDiePlugin.class);
    private final CustomConfig fallenPlayerFile;
    private FallenPlayerService fallenPlayerService = new FallenPlayerService();
    
    /**
     * Here this act as a database instead of file in order to improve performance
     */
    private Set<FallenPlayer> listFallenPlayer = new HashSet<>();

    public YmlDB() {
        super();
        fallenPlayerFile = new CustomConfig("fallenPlayers.yml");
    }
    /**
     * load the data of /plugin/banwhendie/fallenPlayers.yml to the array in-memory
     */
    @Override
    public void connect() {
    	fallenPlayerFile.saveDefault();
    	fallenPlayerFile.reload();
    	
    	List<Map<String,Object>> fallenPlayerListCasted = CollectionsUtil.listGenericToListMap(fallenPlayerFile.getConfig().getList("players"));
    	if (fallenPlayerListCasted != null) {
        	for (Map<String,Object> afp : fallenPlayerListCasted) {
        	    String ip = afp.get("ip") != null ? (String)afp.get("ip") : null;
                String nickname = afp.get("nickname") != null ? (String)afp.get("nickname") : null;
                String banTime = afp.get("banTime") != null ? (String)afp.get("banTime") : null;
                
                String fallenDate = afp.get("fallenDate") != null ? (String)afp.get("fallenDate") : null;
                String releaseDate = afp.get("releaseDate") != null ? (String)afp.get("releaseDate") : null;
                
        	    FallenPlayer fp = new FallenPlayer();
        	    
        	    fp.setIp(ip);
        	    fp.setNickname(nickname);
        	    fp.setBanTime(banTime);
        	    fp.setFallenDate(DateUtility.getLocalDateTimeFromString(fallenDate));
        	    fp.setReleaseDate(DateUtility.getLocalDateTimeFromString(releaseDate));
        	    
        	    listFallenPlayer.add(fp);
        	}
    	}    	
    }
    /**
     * save yml on disconnect
     */
    @Override
    public void disconnect() {
        List<Map<String,Object>> fallenPlayerListMap = listFallenPlayer.stream().map(fp->fp.toMap()).collect(Collectors.toList());
        
        fallenPlayerFile.getConfig().set("players", fallenPlayerListMap);
    	fallenPlayerFile.save();
    }
    /**
     * Add the fallen player to the in-memory array of fallenPlayers
     */
	@Override
	public void saveFallenPlayer(Player player) {
	    if (player != null) {  	    
    	    /*List<Map<String,Object>> fallenPlayerListCasted = CollectionsUtil.listGenericToListMap(fallenPlayerFile.getConfig().getList("players"));
    	    
    	    FallenPlayer fallenPlayer = fallenPlayerService.getFallenPlayerFromPlayer(player);
    	    Map<String, Object> fallenPlayerMap = fallenPlayer.toMap();
    	    fallenPlayerListCasted.add(fallenPlayerMap);
    	    
    	    fallenPlayerFile.getConfig().set("players", fallenPlayerListCasted);
    	    fallenPlayerFile.save();
    	    */
	        
	        FallenPlayer fallenPlayer = fallenPlayerService.getFallenPlayerFromPlayer(player);
	        listFallenPlayer.remove(fallenPlayer);
	        listFallenPlayer.add(fallenPlayer);
	    }
	    //DEBUG
	    System.out.println("Fallen Players: "+listFallenPlayer);
	}
	@Override
	public FallenPlayer findFallenPlayerByPlayer(Player player) {
	    FallenPlayer fallenPlayerToFind = new FallenPlayer(player.getAddress().getHostString(),player.getName());
	    
	    //DEBUG
	    System.out.println("fallenPlayerToFind: "+fallenPlayerToFind);
	    System.out.println("this.listFallenPlayer"+this.listFallenPlayer);
	    
	    FallenPlayer fallenPlayer = null;
	    
	    for (FallenPlayer fallenPlayerFor : new ArrayList<>(this.listFallenPlayer)) {
	        if (fallenPlayerFor.equals(fallenPlayerToFind)) {
	            fallenPlayer = fallenPlayerFor;
	        }
	    }
	    
	    return fallenPlayer;
	}
	@Override
	public List<FallenPlayer> findAllPlayers() {
	    return new ArrayList<>(this.listFallenPlayer);
	}
    public Set<FallenPlayer> getListFallenPlayer() {
        return listFallenPlayer;
    }
    public void setListFallenPlayer(Set<FallenPlayer> listFallenPlayer) {
        this.listFallenPlayer = listFallenPlayer;
    }
}