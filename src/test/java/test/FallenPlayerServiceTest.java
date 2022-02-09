package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.lucke01.banwhendie.services.FallenPlayerService;
import com.lucke01.banwhendie.util.DateUtility;
public class FallenPlayerServiceTest {
    @Test
    public void convertBanTimeGetMilisecond_test() {
        assertEquals(2, 2);
        
        FallenPlayerService fallenPlayerService = new FallenPlayerService();
       
        assertEquals(fallenPlayerService.convertBanTimeInSeconds("1S"), 1);
        assertEquals(fallenPlayerService.convertBanTimeInSeconds("2M"), 120);
        assertEquals(fallenPlayerService.convertBanTimeInSeconds("3H"), 10800);
        assertEquals(fallenPlayerService.convertBanTimeInSeconds("4D"), 345600);
        assertEquals(fallenPlayerService.convertBanTimeInSeconds(""), 0);
    }
}
