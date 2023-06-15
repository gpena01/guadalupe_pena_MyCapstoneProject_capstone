package kanban_board.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Task implements Serializable {
    // create fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskId;
    private String assignTo;
    private String description;
    private Date dueDate;

//    @ManyToOne
//    private Column column;

    // create constructor(s)
    public Task(String assignTo, String description, Date dueDate) {
        super();
        this.assignTo = assignTo;
        this.description = description;
        this.dueDate = dueDate;
    }

    // create getters and setters
    public String getAssignTo() {
        return assignTo;
    }
    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public long getTaskId() {
        return taskId;
    }
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
