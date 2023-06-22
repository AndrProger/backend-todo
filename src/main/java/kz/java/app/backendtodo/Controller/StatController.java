package kz.java.app.backendtodo.Controller;

import kz.java.app.backendtodo.entity.Priority;
import kz.java.app.backendtodo.entity.Stat;
import kz.java.app.backendtodo.repo.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/stat")
public class StatController {


    private StatRepository statRepository;

    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }
    @GetMapping("getOne")
    public ResponseEntity<Stat> findById(){
        Stat stat;
        try {
            stat =statRepository.findById(1L).get();
        }
        catch (NoSuchElementException e){
            return new ResponseEntity("not found id:1", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(stat);
    }
}
