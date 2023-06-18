package kz.java.app.backendtodo.Controller;

import kz.java.app.backendtodo.entity.Category;
import kz.java.app.backendtodo.repo.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
