package guide.springboot.sample.controller;

import guide.springboot.sample.tasks.Task;
import guide.springboot.sample.tasks.TaskAttributes;
import guide.springboot.sample.tasks.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@Validated
class TaskController {
    private final TaskService taskService;

    TaskController(final TaskService taskService){this.taskService = taskService;}

    @GetMapping
    List<Task> retrieveAll(){
        //List<TaskJson>
        //서비스를 호출해서 전체 데이터 불러와야해
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    ResponseEntity retrieveById(@PathVariable("id") final UUID id){
        return ResponseEntity.of(taskService.select(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Map<String, UUID> create(@RequestBody final TaskAttributes taskAttributes){
        //var id = taskService.insert(taskAttributes);
        //return new TaskIdentifier(id);
        taskAttributes.setStatus("active");
        return Map.of("id", taskService.insert(taskAttributes));
    }

    @PutMapping("/{id}")
    TaskAttributes update(
            @PathVariable("id") final UUID id,
            @RequestBody final TaskAttributes taskAttributes
    ){
        return taskService.update(id, taskAttributes);
    }

    @PatchMapping("/{id}")
    TaskAttributes patch(
            @PathVariable("id") final UUID id,
            @RequestBody final TaskAttributes taskAttributes
    ){
        return taskService.patch(id, taskAttributes);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") final UUID id){
        taskService.delete(id);
    }
}
