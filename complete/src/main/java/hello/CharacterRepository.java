package hello;

import hello.Characters;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends CrudRepository<Characters, Integer> {
    Characters findByName(String name);

    void deleteByName(String name);
}
