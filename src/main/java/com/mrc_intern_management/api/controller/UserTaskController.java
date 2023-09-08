package MRC.InternApp.Controller;

import MRC.InternApp.Entity.Task;
import MRC.InternApp.Entity.UserTask;
import MRC.InternApp.Entity.Users;
import MRC.InternApp.Service.userTaskService;
import MRC.InternApp.repositories.TaskRepository;
import MRC.InternApp.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/userTask")
public class userTaskController {


    @Autowired
    private userTaskService userTaskService;
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

   
    // Assign a task to a user
    @PostMapping("{taskId}/assign/{userId}")
    public ResponseEntity<String> assignTaskToUser(@RequestParam Long userId, @RequestParam Long taskId) {
        // Fetch user and task based on IDs (You'll need to implement this)
        Users user = userRepository.findById(userId).orElse(null);
        Task task = taskRepository.findById(taskId).orElse(null);
        if (user == null || task == null) {
            return ResponseEntity.badRequest().body("Task ID " + taskId + " not assigned to User ID " + userId);
        }
        UserTask assignedTask = userTaskService.assignTaskToUser(user, task);
        return ResponseEntity.ok("Task ID " + taskId + " assigned to User ID " + userId);
    }


    @GetMapping("/assigned-tasks/{userId}")
    public ResponseEntity<List<String>> getTasksAssignedToUser(@PathVariable Long userId) {
        List<Task> tasks = userTaskService.getTasksAssignedToUser(userId);

        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build(); // No tasks assigned to the user
        }

        // Extract task IDs and format them into strings
        List<String> assignedTasks = tasks.stream()
                .map(task -> "The task assigned to user ID " + userId + " is task ID " + task.getId())
                .collect(Collectors.toList());

        return ResponseEntity.ok(assignedTasks);
    }

    //Updating assigned task completion
    @PutMapping("/assigned-tasks/{userId}/{taskId}/complete")
    public ResponseEntity<String> updateTaskCompletionStatus(@PathVariable Long userId, @PathVariable Long taskId, @RequestParam Boolean completed) {
        boolean updated = userTaskService.updateTaskCompletionStatus(userId, taskId, completed);
        if (updated) {
            return ResponseEntity.ok("Task ID " + taskId + " completion status updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/assigned-tasks/{userId}/{taskId}")
    public ResponseEntity<String> deleteTaskAssignedToUser(@PathVariable Long userId, @PathVariable Long taskId) {
        boolean deleted = userTaskService.deleteTaskAssignedToUser(userId, taskId);
        if (deleted) {
            return ResponseEntity.ok("Task ID " + taskId + " assigned to User ID " + userId + " deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/completed-users-and-tasks")
    public ResponseEntity<List<String>> getCompletedUsersAndTasks() {
        List<String> completedUsersAndTasks = userTaskService.getCompletedUsersAndTasks();
        if (completedUsersAndTasks.isEmpty()) {
            return ResponseEntity.noContent().build(); // No completed tasks
        }
        return ResponseEntity.ok(completedUsersAndTasks);
    }

    @GetMapping("/notcompleted-users-and-tasks")
    public ResponseEntity<List<String>> getNotCompletedUsersAndTasks() {
        List<String> notCompletedUsersAndTasks = userTaskService.getNotCompletedUsersAndTasks();
        if (notCompletedUsersAndTasks.isEmpty()) {
            return ResponseEntity.noContent().build(); // No completed tasks
        }
        return ResponseEntity.ok(notCompletedUsersAndTasks);
    }

    
}
