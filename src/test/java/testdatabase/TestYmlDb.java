package testdatabase;

import java.util.Set;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.lucke01.banwhendie.database.Database;
import com.lucke01.banwhendie.database.YmlDB;
import com.lucke01.banwhendie.dto.FallenPlayer;

public class TestYmlDb {
    YmlDB database = null;
    Set listFallenPlayer = new HashSet<>();
    
    
    @BeforeEach
    public void init() {
        this.database = (YmlDB)Database.create("yml");
        
        listFallenPlayer.add(new FallenPlayer("ip","luck"));
        listFallenPlayer.add(new FallenPlayer("ip","luck2"));
        
        this.database.setListFallenPlayer(listFallenPlayer);
    }
}
