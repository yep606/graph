package base.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usr" )
@Data
public class User {
    public User(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long telegramId;
    private String firstName;
    private String lastName;
    private String username;

    @OneToMany(mappedBy = "user")
    private Set<Task> tasks;
}
