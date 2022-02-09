package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.lucke01.banwhendie.dto.FallenPlayer;
import com.lucke01.banwhendie.util.CollectionsUtil;

public class FallenPlayerEqualTest {
    public static void main (String ar[]) {
        FallenPlayer f1 = new FallenPlayer("ip1");
        FallenPlayer f_2 = new FallenPlayer("ip1");
        
        FallenPlayer f11 = new FallenPlayer("ip1","name1");
        FallenPlayer f11_2 = new FallenPlayer("ip1","name1");
        
        FallenPlayer fname1 = new FallenPlayer(null,"name1");
        FallenPlayer fname1_2 = new FallenPlayer("","name1");
        FallenPlayer fname1_3 = new FallenPlayer();
        fname1_3.setNickname("name1");
        
        System.out.println(fname1.equals(fname1_3));
        
        Set<FallenPlayer> listFallenPlayer = new HashSet<>();
        
        listFallenPlayer.add(new FallenPlayer("ip","luck"));
        listFallenPlayer.add(new FallenPlayer("ip","luck2"));
        
        List<Map<String,Object>> fallenPlayerListMap = listFallenPlayer.stream().map(fp->fp.toMap()).collect(Collectors.toList());
        
        System.out.println(fallenPlayerListMap);
    }
}
