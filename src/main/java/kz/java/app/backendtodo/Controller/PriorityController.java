package kz.java.app.backendtodo.Controller;

import kz.java.app.backendtodo.entity.Priority;
import kz.java.app.backendtodo.repo.PriorityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
