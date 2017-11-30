package hello;

import hello.Buildings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends CrudRepository<Buildings,Integer> {
    Buildings findByName(String name);
    void deleteByName(String name);
}
