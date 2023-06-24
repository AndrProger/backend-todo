package kz.java.app.backendtodo.Controller;

import kz.java.app.backendtodo.entity.Category;
import kz.java.app.backendtodo.entity.Priority;
import kz.java.app.backendtodo.entity.Task;
import kz.java.app.backendtodo.repo.TaskRepository;
import kz.java.app.backendtodo.search.PrioritySearchValue;
import kz.java.app.backendtodo.search.TaskSearchValues;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task){
        if(task.getId()!=null && task.getId()!=0){
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if(task.getTitle()==null || task.getTitle().trim().length()==0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        taskRepository.save(task);
        return new ResponseEntity(HttpStatus.OK);

    }

    @PutMapping("/update")
    public ResponseEntity<Task> update(@RequestBody Task task){
        if(task.getId()==null || task.getId()==0){
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if(task.getTitle()==null || task.getTitle().trim().length()==0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        if(!taskRepository.existsById(task.getId())){
            return new ResponseEntity("missed obj on db", HttpStatus.NOT_ACCEPTABLE);
        }
        taskRepository.save(task);

        return new ResponseEntity(HttpStatus.OK);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.deleteById(id);
            return new ResponseEntity("deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity("Category not found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Task> findById(@PathVariable("id") Long id){
        Task task;
        try {
            task =taskRepository.findById(id).get();
        }
        catch (NoSuchElementException e){
            return new ResponseEntity("not found id:"+id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Task>> search(@RequestBody TaskSearchValues taskSearchValues){

        System.out.println(taskSearchValues);

        return  ResponseEntity.ok(
                taskRepository.findByFilter(
                        taskSearchValues.getText(),
                        taskSearchValues.getCompleted(),
                        taskSearchValues.getPriorityId(),
                        taskSearchValues.getCategoryId()
                ));
    }

}
