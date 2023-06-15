package kanban_board.repository;

import kanban_board.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByTaskId(long taskId);
    Task findByAssignTo(String assignTo);
}
