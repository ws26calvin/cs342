package models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by ws26 on 5/18/2017.
 */
@Entity
public class Position {
    private String playerid;
    private String postion;

    @Basic
    @Column(name = "PLAYERID")
    public String getPlayerid() {
        return playerid;
    }

    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }

    @Basic
    @Column(name = "POSTION")
    public String getPostion() {
        return postion;
    }

    public void setPostion(String postion) {
        this.postion = postion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (playerid != null ? !playerid.equals(position.playerid) : position.playerid != null) return false;
        if (postion != null ? !postion.equals(position.postion) : position.postion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playerid != null ? playerid.hashCode() : 0;
        result = 31 * result + (postion != null ? postion.hashCode() : 0);
        return result;
    }
}
