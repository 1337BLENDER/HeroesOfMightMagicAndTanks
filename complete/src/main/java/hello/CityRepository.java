package hello;

import hello.Cities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<Cities, Integer> {
    Cities findByName(String name);
    void deleteByName(String name);
}
