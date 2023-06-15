package kanban_board.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Column implements Serializable {

    // create fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long columnId;
    private String columnName;
    // stores cards present in the column
    @OneToMany(cascade = CascadeType.ALL)
    private List<Card> cards;

    //    @ManyToOne
//    private Board board;
    // create constructor(s)
    public Column() {
    }

    public Column(String columnName, List<Card> cards) {
        super();
        this.columnName = columnName;
        this.cards = cards;
    }

    // create getters and setters
    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
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
