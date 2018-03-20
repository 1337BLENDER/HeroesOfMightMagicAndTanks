package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LocationsService {
    private LocationRepository locationRepository;

    @Autowired
    public LocationsService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public LocationsService() {
    }

    /**
     * Find and return one location with given id
     *
     * @param id of required location
     * @return founded location
     */

    public Locations getById(int id) {
        return locationRepository.findOne(id);
    }

    /**
     * Find and return one location with given name
     *
     * @param name of required location
     * @return founded location
     */

    public Locations getByName(String name) {
        return locationRepository.findByName(name);
    }

    /**
     * Find and return all the locations
     *
     * @return locations
     */

    public Iterable<Locations> getAll() {
        return locationRepository.findAll();
    }

    /**
     * Save location if it's new or update if it's already exists
     *
     * @param location need to be saved
     * @return saved location
     */

    public Locations saveOrUpdate(Locations location) {
        return locationRepository.save(location);
    }

    /**
     * Remove location with given id
     *
     * @param id of the location
     */

    public void deleteById(int id) {
        locationRepository.delete(id);
    }

    /**
     * Remove location with given name
     *
     * @param name of the location
     */

    public void deleteByName(String name) {
        locationRepository.deleteByName(name);
    }

    /**
     * Remove all the locations
     */

    public void deleteAll() {
        locationRepository.deleteAll();
    }

    /**
     * Remove given location
     *
     * @param location need to be removed
     */

    public void delete(Locations location) {
        locationRepository.delete(location);
    }
}
