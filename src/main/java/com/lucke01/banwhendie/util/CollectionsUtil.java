package com.lucke01.banwhendie.util;

import java.lang.reflect.Field;
import java.util.*;

import com.lucke01.banwhendie.dto.FallenPlayer;

public class CollectionsUtil {
    public static List<Map<String, Object>> listGenericToListMap(List<?> list) {
        if (list == null) {
            return null;
        }
        List<Map<String,Object>> listCasted = new ArrayList<>();
        for (Object obj : list) {
            listCasted.add((Map<String,Object>)obj);
        }
        return listCasted;
    }
    public static <T> T findObjectInSet(Set<T> set, T objectFind) {
        T objectFound = null;
        
        for (Iterator<T> it = set.iterator(); it.hasNext(); ) {
            T objectIteration = it.next();
            if (objectIteration.equals(objectFind)) {
                objectFound = objectIteration;
                break;
            }
        }
        
        return objectFound;
    }
}
