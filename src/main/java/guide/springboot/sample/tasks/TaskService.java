package guide.springboot.sample.tasks;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {

    List<Task> getTasks();

    Optional<TaskAttributes> select(final UUID taskId);

    UUID insert(final TaskAttributes taskAttributes);

    TaskAttributes update(final UUID taskId, final TaskAttributes taskAttributes);

    TaskAttributes patch(final UUID taskId, final TaskAttributes taskAttributes);

    void delete(final UUID taskId);

    class NoEntityException extends RuntimeException{
        public NoEntityException(){
            super();
        }
        public NoEntityException(final Throwable e){
            super(e);
        }
        public  NoEntityException(final String msg){
            super(msg);
        }
    }
}


/*
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public List<Task> getTasks(){
       return taskRepository.findAll();
    }

    public Optional<TaskAttributes> select(final UUID taskId){
        return taskRepository.findById(taskId).map(it->{
            final var taskAttributes = new TaskAttributes();
            taskAttributes.setDetails(it.getDetails());
            taskAttributes.setStatus(it.getStatus());
            return taskAttributes;
        });
    }

    public UUID insert(final TaskAttributes taskAttributes){
        final var task = new Task();
        task.setDetails(taskAttributes.getDetails());
        task.setStatus(taskAttributes.getStatus());

        final var storedTask = taskRepository.save(task);

        return storedTask.getId();
    }

    public TaskAttributes update(final UUID taskId, final TaskAttributes taskAttributes){
        final var broughtTask = taskRepository.findById(taskId).orElseThrow();

        broughtTask.setDetails(taskAttributes.getDetails());
        broughtTask.setStatus(taskAttributes.getStatus());

        final var saved = taskRepository.save(broughtTask);

        final var savedTaskAttributes = new TaskAttributes();
        savedTaskAttributes.setDetails(saved.getDetails());
        savedTaskAttributes.setStatus(saved.getStatus());
        return savedTaskAttributes;
    }

    public TaskAttributes patch(final UUID taskId, final TaskAttributes taskAttributes){
        final var broughtTask = taskRepository.findById(taskId).orElseThrow();

        if(taskAttributes.getDetails() != null){
            broughtTask.setDetails(taskAttributes.getDetails());
        }
        if(taskAttributes.getStatus() != null){
            broughtTask.setStatus(taskAttributes.getStatus());
        }

        final var saved = taskRepository.save(broughtTask);

        final var savedTaskAttributes = new TaskAttributes();
        savedTaskAttributes.setDetails(saved.getDetails());
        savedTaskAttributes.setStatus(saved.getStatus());
        return savedTaskAttributes;
    }

    public void delete(final UUID taskId){
        taskRepository.deleteById(taskId);
    }
}
*/