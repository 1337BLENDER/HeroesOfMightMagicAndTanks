package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BuildingsInCitiesService {
    private BuildingsInCitiesRepository buildingsInCityRepository;

    @Autowired
    public BuildingsInCitiesService(BuildingsInCitiesRepository buildingsInCityRepository) {
        this.buildingsInCityRepository = buildingsInCityRepository;
    }

    public BuildingsInCitiesService() {
    }

    /**
     * Find and return one buildingsInCities entity with given id
     *
     * @param id of required buildingsInCities entity
     * @return founded buildingsInCities entity
     */

    public BuildingsInCities getById(int id) {
        return buildingsInCityRepository.findOne(id);
    }

    /**
     * Find and return all the buildingsInCities entities
     *
     * @return buildingsInCities entities
     */

    public Iterable<BuildingsInCities> getAll() {
        return buildingsInCityRepository.findAll();
    }

    /**
     * Save buildingsInCities entity if it's new or update if it's already exists
     *
     * @param buildingsInCity need to be saved
     * @return saved buildingsInCities entity
     */

    public BuildingsInCities saveOrUpdate(BuildingsInCities buildingsInCity) {
        return buildingsInCityRepository.save(buildingsInCity);
    }

    /**
     * Remove buildingsInCities entity with given id
     *
     * @param id of the buildingsInCities entity
     */

    public void deleteById(int id) {
        buildingsInCityRepository.delete(id);
    }

    /**
     * Remove all the buildingsInCities entities
     */

    public void deleteAll() {
        buildingsInCityRepository.deleteAll();
    }

    /**
     * Remove given buildingsInCities entity
     *
     * @param buildingsInCity need to be removed
     */

    public void delete(BuildingsInCities buildingsInCity) {
        buildingsInCityRepository.delete(buildingsInCity);
    }

    /**
     * Find and return all buildingInCities entities with given city
     *
     * @param city need to be found
     * @return buildingInCities entities
     */

    public Iterable<BuildingsInCities> getAllByCity(Cities city) {
        return buildingsInCityRepository.getAllByCity(city);
    }
}
