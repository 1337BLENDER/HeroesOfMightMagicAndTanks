package hello;

import hello.Army;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmyRepository extends CrudRepository<Army, Integer> {
    public Army findFirstById(int id);
}
