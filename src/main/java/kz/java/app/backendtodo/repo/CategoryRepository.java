package kz.java.app.backendtodo.repo;

import kz.java.app.backendtodo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query("SELECT c FROM Category c WHERE " +
            "(:title is  null or :title='' or lower(c.title) like lower(concat('%',:title,'%')))" +
            " ORDER by c.title ASC")
    List<Category> findByTitle(@Param("title") String title);

    List<Category> findAllByOrderByTitleAsc();

}
