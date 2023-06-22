package kz.java.app.backendtodo.repo;

import kz.java.app.backendtodo.entity.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatRepository  extends JpaRepository<Stat,Long> {
}
