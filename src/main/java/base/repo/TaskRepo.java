package base.repo;

import base.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TaskRepo extends CrudRepository<Task, Long> {

    Task findById(long id);
    List<Task> findAll();
}
