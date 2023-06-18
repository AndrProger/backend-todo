package kz.java.app.backendtodo.repo;

import kz.java.app.backendtodo.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends JpaRepository<Priority,Long>
{

}
