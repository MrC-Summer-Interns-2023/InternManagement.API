package MRC.InternApp.Service;

import MRC.InternApp.Entity.Task;

import java.util.List;

public interface taskService {
    List<Task> getAllTasks();
    Task createTask(Task task);
    Task updateTask(Long id, Task updatedUser);
    void deleteTask(Long id);
