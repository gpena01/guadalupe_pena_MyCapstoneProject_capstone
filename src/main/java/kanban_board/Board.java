package kanban_board;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table
public class Board implements Serializable {

    // create fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardId;
    private String boardTitle;
    // a collection to store the columns present in the board
    @OneToMany(cascade = CascadeType.ALL)
    private List<Column> columns;

    // create constructor(s)

    public Board(int boardId, String boardTitle, List<Column> columns) {
        super();
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.columns = columns;
    }

    public Board() {
    }

    // create methods
    public String toString() { return null; }
    // adds a column to the board by appending it to the columns collection
    public void addColumn(Column column) {
        columns.add(column);
    }
    public void removeColumn(Column column) {
        columns.remove(column);
    }
    public int getColumnCount() {
        return 0;
    }
    // returns the column at a specified index in the columns collection
    public int getColumn(int index) {
        return index;
    }
    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
}
