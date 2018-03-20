package hello;

import hello.Levels;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends CrudRepository<Levels, Integer> {
}
