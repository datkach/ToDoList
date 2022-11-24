package code.Model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @Column(name = "task_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_gen")
    @SequenceGenerator(name = "task_gen", sequenceName = "s_task", allocationSize = 1)
    private Integer taskId;

    @Column(name = "deadline", nullable = false)
    @Type(type = "date")
    private Date deadline;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "is_finished", nullable = false)
    private Boolean is_finished;

    @Column(name = "prioritet", nullable = false)
    private Integer prioritet;

    @Column(name = "name", nullable = false)
    private String taskName;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "responsible")
    private Users responsible;

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "project")
    private Project project;

    public Task() {
    }

    public Task(Date deadline, String description, Boolean is_finished, String taskName, Users responsible, Project project, Integer prioritet) {
        this.deadline = deadline;
        this.description = description;
        this.is_finished = is_finished;
        this.taskName = taskName;
        this.responsible = responsible;
        this.project = project;
        this.prioritet = prioritet;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIs_finished() {
        return is_finished;
    }

    public void setIs_finished(Boolean is_finished) {
        this.is_finished = is_finished;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Users getResponsible() {
        return responsible;
    }

    public void setResponsible(Users responsible) {
        this.responsible = responsible;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(Integer prioritet) {
        this.prioritet = prioritet;
    }

    public String getResp() {
        return responsible.getLogin();
    }

    public String getProjectName() {
        return project.getProjectName();
    }
}
