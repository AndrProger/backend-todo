package kz.java.app.backendtodo.Controller;

import kz.java.app.backendtodo.entity.Category;
import kz.java.app.backendtodo.entity.Priority;
import kz.java.app.backendtodo.repo.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/all")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("/add")
    public Category add(@RequestBody Category category){
        return categoryRepository.save(category);

    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id") Long id){
        Category category;
        try {
            category =categoryRepository.findById(id).get();
        }
        catch (NoSuchElementException e){
            return new ResponseEntity("not found id:"+id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(category);
    }
}
