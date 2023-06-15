package kanban_board.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class BoardColumn implements Serializable {

    // create fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long columnId;
    private String columnName;
    // stores cards present in the column.
    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;

    //    @ManyToOne
//    private Board board;
    // create constructor(s)
    public BoardColumn() {
    }

    public BoardColumn(String columnName, List<Task> tasks) {
        super();
        this.columnName = columnName;
        this.tasks = tasks;
    }

    // create getters and setters
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public long getColumnId() {
        return columnId;
    }

    public void setColumnId(long columnId) {
        this.columnId = columnId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

}
