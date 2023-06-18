package kz.java.app.backendtodo.repo;

import kz.java.app.backendtodo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
