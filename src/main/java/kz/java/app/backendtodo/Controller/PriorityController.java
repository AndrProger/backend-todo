package kz.java.app.backendtodo.Controller;

import kz.java.app.backendtodo.entity.Priority;
import kz.java.app.backendtodo.repo.PriorityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private PriorityRepository priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @GetMapping("/test")
    public List<Priority> test(){
        List<Priority> list = priorityRepository.findAll();
        System.out.println("list: " + list);
        return list;
    }
    @PostMapping("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority){
        if(priority.getId()!=null && priority.getId()!=0){
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if(priority.getTitle()==null || priority.getTitle().trim().length()==0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(priorityRepository.save(priority));
    }
    @PutMapping("/update")
    public ResponseEntity<Priority> update(@RequestBody Priority priority){
        if(priority.getId()==null || priority.getId()==0){
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }
        if(!priorityRepository.existsById(priority.getId())){
            return new ResponseEntity("missed obj on db", HttpStatus.NOT_ACCEPTABLE);
        }
        if(priority.getTitle()==null || priority.getTitle().trim().length()==0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> findById(@PathVariable("id") Long id){
        Priority priority;
        try {
            priority =priorityRepository.findById(id).get();
        }
        catch (NoSuchElementException e){
            return new ResponseEntity("not found id:"+id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(priority);
    }

}
