package code.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "team")
public class Team implements Serializable {

    @Id
    @Column(name = "team_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_gen")
    @SequenceGenerator(name = "team_gen", sequenceName = "s_team", allocationSize = 1)
    private Integer teamId;

    @Column(name = "name", nullable = false)
    private String teamName;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "admin")
    private Users admin;


    public Team() {
    }

    public Team(String teamName, Users admin) {
        this.teamName = teamName;
        this.admin = admin;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Users getAdmin() {
        return admin;
    }

    public void setAdmin(Users admin) {
        this.admin = admin;
    }
}
