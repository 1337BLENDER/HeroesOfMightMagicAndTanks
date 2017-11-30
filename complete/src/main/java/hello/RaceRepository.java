package hello;

import hello.Race;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends CrudRepository<Race,Integer> {
    Race findByName(String name);
    void deleteByName(String name);
}
