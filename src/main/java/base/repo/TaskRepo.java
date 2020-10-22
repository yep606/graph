package base.repo;

import base.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TaskRepo extends CrudRepository<Task, Long> {

    Task findById(long id);
    Task findByUserTelegramId(long id);
    Optional<Task> findByUserTelegramIdAndFilled(long id, boolean t);
}
