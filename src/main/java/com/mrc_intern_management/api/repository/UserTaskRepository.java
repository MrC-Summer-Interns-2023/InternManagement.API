package MRC.InternApp.repositories;

import MRC.InternApp.Entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {

    List<UserTask> findByUserId(Long userId);

    UserTask findByUserIdAndTaskId(Long userId, Long taskId);

    @Query("SELECT CONCAT(u.name, ' completed task ', t.name) " + "FROM Users u " + "INNER JOIN UserTask ut ON u.id = ut.user.id " + "INNER JOIN Task t ON ut.task.id = t.id " + "WHERE ut.completed = true")
    List<String> getCompletedUsersAndTasks();

    @Query("SELECT CONCAT(u.name, ' not completed task ', t.name) " + "FROM Users u " + "INNER JOIN UserTask ut ON u.id = ut.user.id " + "INNER JOIN Task t ON ut.task.id = t.id " + "WHERE ut.completed = false")
    List<String> getNotCompletedUsersAndTasks();
}
