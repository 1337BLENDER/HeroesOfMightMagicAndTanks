package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UnitsService {
    private UnitRepository unitRepository;

    @Autowired
    public UnitsService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public UnitsService() {
    }

    /**
     * Find and return one unit with given id
     *
     * @param id of required unit
     * @return founded unit
     */

    public Units getById(int id) {
        return unitRepository.findOne(id);
    }

    /**
     * Find and return one unit with given name
     *
     * @param name of required unit
     * @return founded unit
     */

    public Units getByName(String name) {
        return unitRepository.findByName(name);
    }

    /**
     * Find and return all the units
     *
     * @return units
     */

    public Iterable<Units> getAll() {
        return unitRepository.findAll();
    }

    /**
     * Save unit if it's new or update if it's already exists
     *
     * @param unit need to be saved
     * @return saved unit
     */

    public Units saveOrUpdate(Units unit) {
        return unitRepository.save(unit);
    }

    /**
     * Remove unit with given id
     *
     * @param id of the unit
     */

    public void deleteById(int id) {
        unitRepository.delete(id);
    }

    /**
     * Remove unit with given name
     *
     * @param name of the unit
     */

    public void deleteByName(String name) {
        unitRepository.deleteByName(name);
    }

    /**
     * Remove all the units
     */

    public void deleteAll() {
        unitRepository.deleteAll();
    }

    /**
     * Remove given unit
     *
     * @param unit need to be removed
     */

    public void delete(Units unit) {
        unitRepository.delete(unit);
    }
}
