package hello;

import hello.Locations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Locations,Integer> {
    Locations findByName(String name);
    void deleteByName(String name);
}
