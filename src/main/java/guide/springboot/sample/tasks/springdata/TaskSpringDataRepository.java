package guide.springboot.sample.tasks.springdata;

import guide.springboot.sample.tasks.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TaskSpringDataRepository extends CrudRepository<Task, UUID> {
}
