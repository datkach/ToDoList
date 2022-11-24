package code.Model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "project")
public class Project {

    @Id
    @Column(name = "project_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proj_gen")
    @SequenceGenerator(name = "proj_gen", sequenceName = "s_project", allocationSize = 1)
    private Integer projectId;

    @Column(name = "deadline", nullable = false)
    @Type(type = "date")
    private Date deadline;

    @Column(name = "name", nullable = false)
    private String projectName;

    @Column(name = "is_finished", nullable = false)
    private Boolean is_finished;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "team")
    private Team team;


    public Project() {
    }

    public Project(Date deadline, String projectName, Boolean is_finished, Team team, String description) {
        this.deadline = deadline;
        this.projectName = projectName;
        this.is_finished = is_finished;
        this.team = team;
        this.description = description;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Boolean getIs_finished() {
        return is_finished;
    }

    public void setIs_finished(Boolean is_finished) {
        this.is_finished = is_finished;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeamName() {
        return team.getTeamName();
    }


}