package MRC.InternApp.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="internUser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String role;
    @Column
    private String courseCompleted;

    @OneToMany(mappedBy = "user")
    private Set<UserTask> userTasks = new HashSet<>();
}
