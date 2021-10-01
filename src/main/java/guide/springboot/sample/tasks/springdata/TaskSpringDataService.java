package guide.springboot.sample.tasks.springdata;

import guide.springboot.sample.tasks.Task;
import guide.springboot.sample.tasks.TaskAttributes;
import guide.springboot.sample.tasks.TaskService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TaskSpringDataService implements TaskService {
    private TaskSpringDataRepository    taskSpringDataRepository;

    TaskSpringDataService(TaskSpringDataRepository taskSpringDataRepository) {
        this.taskSpringDataRepository = taskSpringDataRepository;
    }
    @Override
    public List<Task> getTasks() {
        final var taskEntities = taskSpringDataRepository.findAll();

        final var tasks = new ArrayList<Task>();
        for(var task : taskEntities){
            tasks.add(task);
        }

        return tasks;
    }

    @Override
    public Optional<TaskAttributes> select(final UUID taskId) {
        return taskSpringDataRepository.findById(taskId).map(it->{
            final var taskAttributes = new TaskAttributes(it.getDetails(), it.getStatus());
            return taskAttributes;
        });
    }

    @Override
    public UUID insert(final TaskAttributes taskAttributes){

        final var entity = new Task(taskAttributes.getDetails(), taskAttributes.getStatus());
        final var saved = taskSpringDataRepository.save(entity);

        return saved.getId();
    }

    @Override
    public TaskAttributes update(UUID taskId, TaskAttributes taskAttributes) {
        final var broughtTask = taskSpringDataRepository.findById(taskId).orElseThrow();

        broughtTask.setDetails(taskAttributes.getDetails());
        broughtTask.setStatus(taskAttributes.getStatus());

        final var saved = taskSpringDataRepository.save(broughtTask);
        final var savedTaskAttributes = new TaskAttributes(saved.getDetails(), saved.getStatus());

        return savedTaskAttributes;
    }

    @Override
    public TaskAttributes patch(UUID taskId, TaskAttributes taskAttributes) {
        final var broughtTask = taskSpringDataRepository.findById(taskId).orElseThrow();

        if(taskAttributes.getDetails() != null){
            broughtTask.setDetails(taskAttributes.getDetails());
        }
        if(taskAttributes.getStatus() != null){
            broughtTask.setStatus(taskAttributes.getStatus());
        }

        final var saved = taskSpringDataRepository.save(broughtTask);
        final var savedTaskAttributes = new TaskAttributes(saved.getDetails(), saved.getStatus());

        return savedTaskAttributes;
    }

    @Override
    public void delete(final UUID taskId) {
        try{
            taskSpringDataRepository.deleteById(taskId);
        }catch (EmptyResultDataAccessException e){
            throw new NoEntityException(e);
        }
    }
}
