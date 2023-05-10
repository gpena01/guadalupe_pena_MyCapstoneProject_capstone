import java.util.List;

public class Column {

    // create fields
    private String columnName;
    // stores cards present in the column
    private List<Card> cards;
    // create constructor(s)

    // create methods
    public void addCard(Card card) {
        cards.add(card);
    }
    public void removeCard(Card card) {
        cards.remove(card);
    }
    public void moveCardToColumn(Card card, Column destinationColumn) {}
    // returns number of cards present in the column
    public int getCardCount() {}
    public void isColumnEmpty() {}
    // checks if a specific card is present in the column
    public void hasCard(Card card) {}
    // provides a string representation of the column, including its name and the cards it contains
    @Override
    public String toString() {
        return null;
    }
    public List<Card> getCards() {
        return cards;
    }
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
