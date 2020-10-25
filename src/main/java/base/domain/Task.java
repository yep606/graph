package base.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Task {

    public Task(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime expiration;
    private String description;
    private boolean filled;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usr_id", nullable = false)
    private User user;



}
