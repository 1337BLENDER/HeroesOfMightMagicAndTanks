package hello;

import hello.BuildingsInCities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingsInCitiesRepository extends CrudRepository<BuildingsInCities, Integer> {
    Iterable<BuildingsInCities> getAllByCity(Cities city);
}
