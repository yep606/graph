package base.repo;

import base.domain.Task;
import base.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TaskRepo extends CrudRepository<Task, Long> {

    List<Task> findByUser(User user);
    List<Task> findAll();
}
