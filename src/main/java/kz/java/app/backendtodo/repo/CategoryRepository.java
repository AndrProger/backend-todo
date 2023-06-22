package kz.java.app.backendtodo.repo;

import kz.java.app.backendtodo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

        List<Category> findAllByOrderByTitleAsc();

}
