package kanban_board;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Card {
    // create fields
    private String cardTitle;
    private String description;
    private String assignee;
    private Date dueDate;
    // an integer that represents the priority level of the card
    private int priority;
    // a string status to indicate the current status or stage of the card ("To Do", "In Progress",
    // "Done")
    private String status;

    // create constructor(s)

    // create methods
    public void moveToNextStatus() {}
    public void moveToPreviousStatus() {}
    public void updateStatus(String newStatus) {}
    // checks if the card's due date has passed
    public void isOverdue() {}
    // Override the toString() method to provide a string representation of the card's attributes
    @Override
    public String toString() {
        return null;
    }

    public String getCardTitle() {
        return cardTitle;
    }
    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }
    // sets the priority level of the card
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
