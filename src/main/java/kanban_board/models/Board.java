package kanban_board.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
public class Board implements Serializable {

    // create fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boardId;
    private String boardTitle;
    // a collection to store the columns present in the board
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Column> columns;

    // create constructor(s)
    public Board() {
    }

    public Board(String boardTitle, List<BoardColumn> columns) {
        super();
        this.boardTitle = boardTitle;
//        this.columns = columns;
    }

    // create getters and setters
    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

//    public List<Column> getColumns() {
//        return columns;
//    }
//
//    public void setColumns(List<Column> columns) {
//        this.columns = columns;
//    }

    public long getBoardId() {
        return boardId;
    }
}
