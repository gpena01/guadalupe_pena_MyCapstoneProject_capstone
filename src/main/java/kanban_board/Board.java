package kanban_board;

import java.util.ArrayList;
import java.util.List;

public class Board {

    // create fields
    private String boardTitle;
    // a collection to store the columns present in the board
    private List<Column> columns;

    // create constructor(s)

    // create methods
    // adds a column to the board by appending it to the columns collection
    public void addColumn(Column column) {
        columns.add(column);
    }
    public void removeColumn(Column column) {
        columns.remove(column);
    }
    public int getColumnCount() {}
    // returns the column at a specified index in the columns collection
    public int getColumn(int index) {}
    public

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
}
