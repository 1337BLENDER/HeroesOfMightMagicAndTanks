package hello;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilitiesRepository extends CrudRepository<Abilities, Integer> {
    Abilities findByName(String name);

    void deleteByName(String name);
}
