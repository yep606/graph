package base.repo;

import base.domain.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<Task, Long> {

    Task findById(long id);
    Task findByUserTelegramId(long id);

}
