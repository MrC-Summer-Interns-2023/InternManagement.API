package MRC.InternApp.Service;


import MRC.InternApp.Entity.Task;
import MRC.InternApp.Entity.UserTask;
import MRC.InternApp.Entity.Users;

import java.util.List;

public interface userTaskService {


   UserTask assignTaskToUser(Users user, Task task);
   List<Task> getTasksAssignedToUser(Long userId);
   boolean updateTaskCompletionStatus(Long userId, Long taskId, Boolean completed);
   boolean deleteTaskAssignedToUser(Long userId, Long taskId);
   List<String> getCompletedUsersAndTasks();
   List<String> getNotCompletedUsersAndTasks();
}
