package base.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@ToString(of = {"id", "description"})
@EqualsAndHashCode(of = {"id"})
public class Task {

    public Task(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime expiration;
    private String description;

    @ManyToOne
    @JoinColumn(name = "usr_id", nullable = false)
    private User user;





}
