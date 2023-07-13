package kanban_board.models;

import kanban_board.user.User;

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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="username")
    private User user;

    // create constructor(s)
    public Board() {
    }

    public Board(String boardTitle, User user) {
        super();
        this.boardTitle = boardTitle;
        this.user = user;
    }

    // create getters and setters
    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getBoardId() {
        return boardId;
    }
}
