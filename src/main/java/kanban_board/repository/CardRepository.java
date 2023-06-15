package kanban_board.repository;

import kanban_board.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardId(long cardId);
    Card findByAssignTo(String assignTo);
}
