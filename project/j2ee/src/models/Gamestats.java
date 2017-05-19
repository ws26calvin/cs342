package models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by ws26 on 5/18/2017.
 */
@Entity
public class Gamestats {
    private String gameid;
    private String teamid;
    private String playerid;
    private Long timeplayed;
    private Long fg;
    private Long fga;
    private Long fgPerc;
    private Long tp;
    private Long tpa;
    private Long tpPerc;
    private Long ft;
    private Long fta;
    private Long ftPerc;
    private Long orb;
    private Long drb;
    private Long trb;
    private Long ast;
    private Long stl;
    private Long blk;
    private Long tov;
    private Long foul;
    private Long pts;
    private Long plusminus;

    @Basic
    @Column(name = "GAMEID")
    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    @Basic
    @Column(name = "TEAMID")
    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    @Basic
    @Column(name = "PLAYERID")
    public String getPlayerid() {
        return playerid;
    }

    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }

    @Basic
    @Column(name = "TIMEPLAYED")
    public Long getTimeplayed() {
        return timeplayed;
    }

    public void setTimeplayed(Long timeplayed) {
        this.timeplayed = timeplayed;
    }

    @Basic
    @Column(name = "FG")
    public Long getFg() {
        return fg;
    }

    public void setFg(Long fg) {
        this.fg = fg;
    }

    @Basic
    @Column(name = "FGA")
    public Long getFga() {
        return fga;
    }

    public void setFga(Long fga) {
        this.fga = fga;
    }

    @Basic
    @Column(name = "FG_PERC")
    public Long getFgPerc() {
        return fgPerc;
    }

    public void setFgPerc(Long fgPerc) {
        this.fgPerc = fgPerc;
    }

    @Basic
    @Column(name = "TP")
    public Long getTp() {
        return tp;
    }

    public void setTp(Long tp) {
        this.tp = tp;
    }

    @Basic
    @Column(name = "TPA")
    public Long getTpa() {
        return tpa;
    }

    public void setTpa(Long tpa) {
        this.tpa = tpa;
    }

    @Basic
    @Column(name = "TP_PERC")
    public Long getTpPerc() {
        return tpPerc;
    }

    public void setTpPerc(Long tpPerc) {
        this.tpPerc = tpPerc;
    }

    @Basic
    @Column(name = "FT")
    public Long getFt() {
        return ft;
    }

    public void setFt(Long ft) {
        this.ft = ft;
    }

    @Basic
    @Column(name = "FTA")
    public Long getFta() {
        return fta;
    }

    public void setFta(Long fta) {
        this.fta = fta;
    }

    @Basic
    @Column(name = "FT_PERC")
    public Long getFtPerc() {
        return ftPerc;
    }

    public void setFtPerc(Long ftPerc) {
        this.ftPerc = ftPerc;
    }

    @Basic
    @Column(name = "ORB")
    public Long getOrb() {
        return orb;
    }

    public void setOrb(Long orb) {
        this.orb = orb;
    }

    @Basic
    @Column(name = "DRB")
    public Long getDrb() {
        return drb;
    }

    public void setDrb(Long drb) {
        this.drb = drb;
    }

    @Basic
    @Column(name = "TRB")
    public Long getTrb() {
        return trb;
    }

    public void setTrb(Long trb) {
        this.trb = trb;
    }

    @Basic
    @Column(name = "AST")
    public Long getAst() {
        return ast;
    }

    public void setAst(Long ast) {
        this.ast = ast;
    }

    @Basic
    @Column(name = "STL")
    public Long getStl() {
        return stl;
    }

    public void setStl(Long stl) {
        this.stl = stl;
    }

    @Basic
    @Column(name = "BLK")
    public Long getBlk() {
        return blk;
    }

    public void setBlk(Long blk) {
        this.blk = blk;
    }

    @Basic
    @Column(name = "TOV")
    public Long getTov() {
        return tov;
    }

    public void setTov(Long tov) {
        this.tov = tov;
    }

    @Basic
    @Column(name = "FOUL")
    public Long getFoul() {
        return foul;
    }

    public void setFoul(Long foul) {
        this.foul = foul;
    }

    @Basic
    @Column(name = "PTS")
    public Long getPts() {
        return pts;
    }

    public void setPts(Long pts) {
        this.pts = pts;
    }

    @Basic
    @Column(name = "PLUSMINUS")
    public Long getPlusminus() {
        return plusminus;
    }

    public void setPlusminus(Long plusminus) {
        this.plusminus = plusminus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gamestats gamestats = (Gamestats) o;

        if (gameid != null ? !gameid.equals(gamestats.gameid) : gamestats.gameid != null) return false;
        if (teamid != null ? !teamid.equals(gamestats.teamid) : gamestats.teamid != null) return false;
        if (playerid != null ? !playerid.equals(gamestats.playerid) : gamestats.playerid != null) return false;
        if (timeplayed != null ? !timeplayed.equals(gamestats.timeplayed) : gamestats.timeplayed != null) return false;
        if (fg != null ? !fg.equals(gamestats.fg) : gamestats.fg != null) return false;
        if (fga != null ? !fga.equals(gamestats.fga) : gamestats.fga != null) return false;
        if (fgPerc != null ? !fgPerc.equals(gamestats.fgPerc) : gamestats.fgPerc != null) return false;
        if (tp != null ? !tp.equals(gamestats.tp) : gamestats.tp != null) return false;
        if (tpa != null ? !tpa.equals(gamestats.tpa) : gamestats.tpa != null) return false;
        if (tpPerc != null ? !tpPerc.equals(gamestats.tpPerc) : gamestats.tpPerc != null) return false;
        if (ft != null ? !ft.equals(gamestats.ft) : gamestats.ft != null) return false;
        if (fta != null ? !fta.equals(gamestats.fta) : gamestats.fta != null) return false;
        if (ftPerc != null ? !ftPerc.equals(gamestats.ftPerc) : gamestats.ftPerc != null) return false;
        if (orb != null ? !orb.equals(gamestats.orb) : gamestats.orb != null) return false;
        if (drb != null ? !drb.equals(gamestats.drb) : gamestats.drb != null) return false;
        if (trb != null ? !trb.equals(gamestats.trb) : gamestats.trb != null) return false;
        if (ast != null ? !ast.equals(gamestats.ast) : gamestats.ast != null) return false;
        if (stl != null ? !stl.equals(gamestats.stl) : gamestats.stl != null) return false;
        if (blk != null ? !blk.equals(gamestats.blk) : gamestats.blk != null) return false;
        if (tov != null ? !tov.equals(gamestats.tov) : gamestats.tov != null) return false;
        if (foul != null ? !foul.equals(gamestats.foul) : gamestats.foul != null) return false;
        if (pts != null ? !pts.equals(gamestats.pts) : gamestats.pts != null) return false;
        if (plusminus != null ? !plusminus.equals(gamestats.plusminus) : gamestats.plusminus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gameid != null ? gameid.hashCode() : 0;
        result = 31 * result + (teamid != null ? teamid.hashCode() : 0);
        result = 31 * result + (playerid != null ? playerid.hashCode() : 0);
        result = 31 * result + (timeplayed != null ? timeplayed.hashCode() : 0);
        result = 31 * result + (fg != null ? fg.hashCode() : 0);
        result = 31 * result + (fga != null ? fga.hashCode() : 0);
        result = 31 * result + (fgPerc != null ? fgPerc.hashCode() : 0);
        result = 31 * result + (tp != null ? tp.hashCode() : 0);
        result = 31 * result + (tpa != null ? tpa.hashCode() : 0);
        result = 31 * result + (tpPerc != null ? tpPerc.hashCode() : 0);
        result = 31 * result + (ft != null ? ft.hashCode() : 0);
        result = 31 * result + (fta != null ? fta.hashCode() : 0);
        result = 31 * result + (ftPerc != null ? ftPerc.hashCode() : 0);
        result = 31 * result + (orb != null ? orb.hashCode() : 0);
        result = 31 * result + (drb != null ? drb.hashCode() : 0);
        result = 31 * result + (trb != null ? trb.hashCode() : 0);
        result = 31 * result + (ast != null ? ast.hashCode() : 0);
        result = 31 * result + (stl != null ? stl.hashCode() : 0);
        result = 31 * result + (blk != null ? blk.hashCode() : 0);
        result = 31 * result + (tov != null ? tov.hashCode() : 0);
        result = 31 * result + (foul != null ? foul.hashCode() : 0);
        result = 31 * result + (pts != null ? pts.hashCode() : 0);
        result = 31 * result + (plusminus != null ? plusminus.hashCode() : 0);
        return result;
    }
}
