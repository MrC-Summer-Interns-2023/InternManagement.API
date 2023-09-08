package MRC.InternApp.Service;

import MRC.InternApp.Entity.Task;
import MRC.InternApp.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class taskServiceImp implements taskService{
    
        @Autowired
        private TaskRepository taskRepository;
        
        @Override
        public List<Task> getAllTasks() {
            return taskRepository.findAll();
        }
        
        @Override
        public Task createTask(Task task) {
            System.out.println(task);
            return taskRepository.save(task);
        }
        
        @Override
        public Task updateTask(Long id, Task updatedTask) {
            Task existingTask = taskRepository.findById(id).orElse(null);
            if (existingTask != null) {
                // Update user properties here
                existingTask.setName(updatedTask.getName());
                existingTask.setDescription(updatedTask.getDescription());
                existingTask.setDueDate(updatedTask.getDueDate());
                // Save the updated user
                return taskRepository.save(existingTask);
            }
            return null;
        }

        @Override
        public void deleteTask(Long id) {
            taskRepository.deleteById(id);
        }
    }
