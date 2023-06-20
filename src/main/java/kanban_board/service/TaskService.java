package kanban_board.service;

import kanban_board.models.Board;
import kanban_board.models.Task;
import kanban_board.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public Task updateTask(long taskId, Task updatedTask) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));

        task.setAssignTo(updatedTask.getAssignTo());
        task.setDescription(updatedTask.getDescription());

        updatedTask = taskRepository.save(task);
        return updatedTask;
    }
    public Task getTaskById(long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));
    }
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public void saveTask(Task task) {
        taskRepository.save(task);
    }
    public void deleteTaskById(long taskId) {
        taskRepository.deleteById(taskId);
    }
}
