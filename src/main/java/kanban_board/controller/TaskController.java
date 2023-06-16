package kanban_board.controller;

import kanban_board.models.Task;
import kanban_board.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("/newTask")
    public String createTask(Task task) {
        Task createdTask = taskService.createTask(task);
        return "home";
    }
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable long taskId) {
        Task task = taskService.getTaskById(taskId);
        return ResponseEntity.ok(task);
    }
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable long taskId, @RequestBody Task updatedTask) {
        Task task = taskService.updateTask(taskId, updatedTask);
        return ResponseEntity.ok(task);
    }
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}
