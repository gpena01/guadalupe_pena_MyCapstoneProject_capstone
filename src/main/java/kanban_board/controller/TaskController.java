package kanban_board.controller;

import kanban_board.models.Task;
import kanban_board.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String createTask(Model model) {
        // this method will allow user to add a new task
        Task task = new Task();
        model.addAttribute("task", task);
        return "home";
    }

}
