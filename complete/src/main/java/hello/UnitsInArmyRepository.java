package hello;

import hello.UnitsInArmy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitsInArmyRepository extends CrudRepository<UnitsInArmy, Integer>{
    Iterable<UnitsInArmy> findAllByArmy(Army army);
}
