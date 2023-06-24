package kz.java.app.backendtodo.repo;

import kz.java.app.backendtodo.entity.Category;
import kz.java.app.backendtodo.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<Priority,Long>
{
    @Query("SELECT p FROM Priority p WHERE " +
            "(:title is  null or :title='' or lower(p.title) like lower(concat('%',:title,'%')))" +
            " ORDER by p.title ASC")
    List<Priority> findByTitle(@Param("title") String title);
    List<Priority> findAllByOrderByIdAsc();
}
