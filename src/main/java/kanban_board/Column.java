package kanban_board;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
public class Column implements Serializable {

    // create fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int columnId;
    private String columnName;
    // stores cards present in the column
    private List<Card> cards;
    @ManyToOne
    private Board board;
    // create constructor(s)

    public Column(int columnId, String columnName, List<Card> cards) {
        super();
        this.columnId = columnId;
        this.columnName = columnName;
        this.cards = cards;
    }

    public Column() {
    }

    // create methods
    public void addCard(Card card) {
        cards.add(card);
    }
    public void removeCard(Card card) {
        cards.remove(card);
    }
    public void moveCardToColumn(Card card, Column destinationColumn) {}
    // returns number of cards present in the column
    public int getCardCount() {
        return 0;
    }
    public void isColumnEmpty() {}
    // checks if a specific card is present in the column
    public void hasCard(Card card) {}
    // provides a string representation of the column, including its name and the cards it contains
    public String toString() {
        return null;
    }
    public List<Card> getCards() {
        return cards;
    }
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }

    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
