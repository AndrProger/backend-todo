package kz.java.app.backendtodo.Controller;

import kz.java.app.backendtodo.entity.Category;
import kz.java.app.backendtodo.entity.Priority;
import kz.java.app.backendtodo.repo.CategoryRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/all")
    public List<Category> findAll() {
        return categoryRepository.findAllByOrderByTitleAsc();
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.deleteById(id);
            return new ResponseEntity("deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity("Category not found", HttpStatus.NOT_FOUND);
        }
    }

}
