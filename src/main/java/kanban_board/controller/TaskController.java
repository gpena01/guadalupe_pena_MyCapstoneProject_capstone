package kanban_board.controller;

import kanban_board.models.Task;
import kanban_board.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {
    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("/listTasks")
    public String getAllTasks(Model model) {
        model.addAttribute("listTasks", taskService.getAllTasks());
        return "home";
    }
    @GetMapping("/newTask")
    public String createTask(Model model) {
        // allows user to add a new task
        Task task = new Task();
        model.addAttribute("task", task);
        return "home";
    }
    @GetMapping("/updateTask/{taskId}")
    public String updateTask(@PathVariable long taskId, Model model) {
        // updates card assignTo, description and lastUpdated fields
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task", task);
        return "home";
    }
    @GetMapping("/saveTask")
    public String saveTask(@ModelAttribute("task") Task task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "home";
        }
        // save task
        taskService.saveTask(task);
        return "redirect:/";
    }
    @GetMapping("/deleteTask/{taskId}")
    public String deleteTask(@PathVariable long taskId) {
        this.taskService.deleteTaskById(taskId);
        return "redirect:/";
    }
}
