package models;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

/**
 * Created by ws26 on 5/18/2017.
 */
@Entity
public class Player {
    private String id;
    private String teamid;
    private String firstname;
    private String lastname;
    private Time dob;
    private Long height;
    private Long experience;
    private String college;

    @ManyToOne
    @JoinColumn(name = "TeamID", referencedColumnName = "ID")
    private Team team;



    @ManyToMany
    @JoinTable(name = "Gamestats", schema = "BBDB",
            joinColumns = @JoinColumn(name = "PlayerID", referencedColumnName = "ID", nullable = false))
    private List<Gamestats> gs;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Gamestats> getTeams() {
        return gs;
    }

    public void setTeams(List<Gamestats> gs) {
        this.gs = gs;
    }


    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "FIRSTNAME")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "LASTNAME")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "DOB")
    public Time getDob() {
        return dob;
    }

    public void setDob(Time dob) {
        this.dob = dob;
    }

    @Basic
    @Column(name = "HEIGHT")
    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    @Basic
    @Column(name = "EXPERIENCE")
    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    @Basic
    @Column(name = "COLLEGE")
    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (id != null ? !id.equals(player.id) : player.id != null) return false;
        if (teamid != null ? !teamid.equals(player.teamid) : player.teamid != null) return false;
        if (firstname != null ? !firstname.equals(player.firstname) : player.firstname != null) return false;
        if (lastname != null ? !lastname.equals(player.lastname) : player.lastname != null) return false;
        if (dob != null ? !dob.equals(player.dob) : player.dob != null) return false;
        if (height != null ? !height.equals(player.height) : player.height != null) return false;
        if (experience != null ? !experience.equals(player.experience) : player.experience != null) return false;
        if (college != null ? !college.equals(player.college) : player.college != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (teamid != null ? teamid.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        result = 31 * result + (college != null ? college.hashCode() : 0);
        return result;
    }
}
