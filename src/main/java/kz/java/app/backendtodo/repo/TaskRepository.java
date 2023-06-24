package kz.java.app.backendtodo.repo;

import kz.java.app.backendtodo.entity.Priority;
import kz.java.app.backendtodo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query("SELECT p FROM Task p WHERE " +
            "(:title is  null or :title='' or lower(p.title) like lower(concat('%',:title,'%'))) and " +
            "(:completed is null or p.completed=:completed) and" +
            "(:priorityId is null or p.priority=:priorityId) and" +
            "(:categoryId is null or p.category=:categoryId)" +
            " ORDER by p.title ASC")
    List<Task> findByFilter(@Param("title") String title,
                               @Param("completed") Integer completed,
                               @Param("priorityId") Long priorityId,
                               @Param("categoryId") Long categoryId

    );
}
