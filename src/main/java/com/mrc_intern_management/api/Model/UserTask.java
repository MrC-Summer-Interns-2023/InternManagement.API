package MRC.InternApp.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="intern_user_task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserTask {
    @Id
    @GeneratedValue
    @Column
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private Users user;
    @ManyToOne
    @JoinColumn(name="task_id")
    private Task task;
    @Column
    private boolean completed;
}
