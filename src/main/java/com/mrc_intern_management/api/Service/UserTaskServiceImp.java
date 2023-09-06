package MRC.InternApp.Service;

import MRC.InternApp.Entity.Task;
import MRC.InternApp.Entity.UserTask;
import MRC.InternApp.Entity.Users;
import MRC.InternApp.repositories.TaskRepository;
import MRC.InternApp.repositories.UserTaskRepository;
import MRC.InternApp.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class userTaskServiceImp implements userTaskService {

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserTaskRepository userTaskRepository;

    @Override
    @Transactional
    public boolean updateTaskCompletionStatus(Long userId, Long taskId, Boolean completed) {
        // First, check if the task is assigned to the specified user
        UserTask userTask = userTaskRepository.findByUserIdAndTaskId(userId, taskId);
        if (userTask != null) {
            // Task is assigned to the user, update completion status
            userTask.setCompleted(completed);
            userTaskRepository.save(userTask); // Save the updated UserTask entity
            return true; // Task completion status updated successfully
        } else {
            return false; // Task not found or not assigned to the user
        }
    }

    @Override
    public boolean deleteTaskAssignedToUser(Long userId, Long taskId) {
        UserTask userTask = userTaskRepository.findByUserIdAndTaskId(userId, taskId);
        if (userTask != null) {
            userTaskRepository.delete(userTask);
            return true;
        }
        return false;
    }


    @Override
    public UserTask assignTaskToUser(Users user, Task task) {
        UserTask userTask = new UserTask();
        userTask.setUser(user);
        userTask.setTask(task);
        userTask.setCompleted(false);
        // You can set this based on your logic
        return userTaskRepository.save(userTask);
    }

    @Override
    public List<Task> getTasksAssignedToUser(Long userId) {
        List<UserTask> userTasks = userTaskRepository.findByUserId(userId);
        return userTasks.stream()
                .map(UserTask::getTask)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getCompletedUsersAndTasks() {
        return userTaskRepository.getCompletedUsersAndTasks(); }

    @Override
    public List<String> getNotCompletedUsersAndTasks() {
        return userTaskRepository.getNotCompletedUsersAndTasks(); }
}
