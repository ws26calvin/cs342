package models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;

/**
 * Created by ws26 on 5/18/2017.
 */
@Entity
public class Game {
    private String id;
    private Time dayplayed;
    private String awayteam;
    private Long awayscore;
    private String hometeam;
    private Long homescore;
    private String winnerid;
    private String loserid;
    private String overtimes;

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DAYPLAYED")
    public Time getDayplayed() {
        return dayplayed;
    }

    public void setDayplayed(Time dayplayed) {
        this.dayplayed = dayplayed;
    }

    @Basic
    @Column(name = "AWAYTEAM")
    public String getAwayteam() {
        return awayteam;
    }

    public void setAwayteam(String awayteam) {
        this.awayteam = awayteam;
    }

    @Basic
    @Column(name = "AWAYSCORE")
    public Long getAwayscore() {
        return awayscore;
    }

    public void setAwayscore(Long awayscore) {
        this.awayscore = awayscore;
    }

    @Basic
    @Column(name = "HOMETEAM")
    public String getHometeam() {
        return hometeam;
    }

    public void setHometeam(String hometeam) {
        this.hometeam = hometeam;
    }

    @Basic
    @Column(name = "HOMESCORE")
    public Long getHomescore() {
        return homescore;
    }

    public void setHomescore(Long homescore) {
        this.homescore = homescore;
    }

    @Basic
    @Column(name = "WINNERID")
    public String getWinnerid() {
        return winnerid;
    }

    public void setWinnerid(String winnerid) {
        this.winnerid = winnerid;
    }

    @Basic
    @Column(name = "LOSERID")
    public String getLoserid() {
        return loserid;
    }

    public void setLoserid(String loserid) {
        this.loserid = loserid;
    }

    @Basic
    @Column(name = "OVERTIMES")
    public String getOvertimes() {
        return overtimes;
    }

    public void setOvertimes(String overtimes) {
        this.overtimes = overtimes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (id != null ? !id.equals(game.id) : game.id != null) return false;
        if (dayplayed != null ? !dayplayed.equals(game.dayplayed) : game.dayplayed != null) return false;
        if (awayteam != null ? !awayteam.equals(game.awayteam) : game.awayteam != null) return false;
        if (awayscore != null ? !awayscore.equals(game.awayscore) : game.awayscore != null) return false;
        if (hometeam != null ? !hometeam.equals(game.hometeam) : game.hometeam != null) return false;
        if (homescore != null ? !homescore.equals(game.homescore) : game.homescore != null) return false;
        if (winnerid != null ? !winnerid.equals(game.winnerid) : game.winnerid != null) return false;
        if (loserid != null ? !loserid.equals(game.loserid) : game.loserid != null) return false;
        if (overtimes != null ? !overtimes.equals(game.overtimes) : game.overtimes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dayplayed != null ? dayplayed.hashCode() : 0);
        result = 31 * result + (awayteam != null ? awayteam.hashCode() : 0);
        result = 31 * result + (awayscore != null ? awayscore.hashCode() : 0);
        result = 31 * result + (hometeam != null ? hometeam.hashCode() : 0);
        result = 31 * result + (homescore != null ? homescore.hashCode() : 0);
        result = 31 * result + (winnerid != null ? winnerid.hashCode() : 0);
        result = 31 * result + (loserid != null ? loserid.hashCode() : 0);
        result = 31 * result + (overtimes != null ? overtimes.hashCode() : 0);
        return result;
    }
}
