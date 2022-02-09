package com.lucke01.banwhendie.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import com.lucke01.banwhendie.util.DateUtility;

public class FallenPlayer implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nickname;
    private String ip;
    private String banTime;
    private LocalDateTime fallenDate;
    private LocalDateTime releaseDate;
    
    public FallenPlayer() {
        
    }
    public FallenPlayer(String nickname, String ip, String banTime, LocalDateTime fallenDate) {
        super();
        this.nickname = nickname;
        this.ip = ip;
        this.banTime = banTime;
        this.fallenDate = fallenDate;
    }
    public FallenPlayer(String ip, String nickname) {
        super();
        this.nickname = nickname;
        this.ip = ip;
    }
    public FallenPlayer(String ip) {
        super();
        this.ip = ip;
    }
    
    public Map<String, Object> toMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("nickname", this.nickname);
        map.put("ip", this.ip);
        map.put("banTime", this.banTime);
        map.put("fallenDate", DateUtility.formatLocalDateTime(this.fallenDate));
        map.put("releaseDate", DateUtility.formatLocalDateTime(this.releaseDate));
        
        return map;
    }    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FallenPlayer other = (FallenPlayer) obj;
        if (ip == null) {
            if (other.ip != null)
                return false;
        } else if (!ip.equals(other.ip))
            return false;
        if (nickname == null) {
            if (other.nickname != null)
                return false;
        } else if (!nickname.equals(other.nickname))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "FallenPlayer [nickname=" + nickname + ", ip=" + ip + ", banTime=" + banTime + ", fallenDate="
                + fallenDate + ", releaseDate=" + releaseDate + "]";
    }
    //getters and setters
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getBanTime() {
        return banTime;
    }
    public void setBanTime(String banTime) {
        this.banTime = banTime;
    }
    public LocalDateTime getFallenDate() {
        return fallenDate;
    }
    public void setFallenDate(LocalDateTime fallenDate) {
        this.fallenDate = fallenDate;
    }
    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }
}
