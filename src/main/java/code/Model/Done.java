package code.Model;

import javax.persistence.*;

@Entity
@Table(name = "done")
public class Done {

    @Id
    @Column(name = "done_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "done_gen")
    @SequenceGenerator(name = "done_gen", sequenceName = "s_done", allocationSize = 1)
    private Integer doneId;

    @ManyToOne(targetEntity = Task.class)
    @JoinColumn(name = "task")
    private Task task;

    public Done(Task task) {
        this.task = task;
    }

    public Done() {
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Integer getDoneId() {
        return doneId;
    }

    public void setDoneId(Integer doneId) {
        this.doneId = doneId;
    }
}
