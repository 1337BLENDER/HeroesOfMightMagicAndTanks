package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AbilitiesService {
    private AbilitiesRepository abilityRepository;

    @Autowired
    public AbilitiesService(AbilitiesRepository abilityRepository) {
        this.abilityRepository = abilityRepository;
    }

    public AbilitiesService() {
    }

    /**
     * Find and return one ability with given id
     *
     * @param id of required ability
     * @return founded ability
     */
    public Abilities getById(int id) {
        return abilityRepository.findOne(id);
    }

    /**
     * Find and return one ability with given name
     *
     * @param name of required ability
     * @return founded ability
     */

    public Abilities getByName(String name) {
        return abilityRepository.findByName(name);
    }

    /**
     * Find and return all the abilities
     *
     * @return abilities
     */

    public Iterable<Abilities> getAll() {
        return abilityRepository.findAll();
    }

    /**
     * Save ability if it's new or update if it's already exists
     *
     * @param Ability need to be saved
     * @return saved ability
     */

    public Abilities saveOrUpdate(Abilities Ability) {
        return abilityRepository.save(Ability);
    }

    /**
     * Remove ability with given id
     *
     * @param id of the ability
     */

    public void deleteById(int id) {
        abilityRepository.delete(id);
    }

    /**
     * Remove ability with given name
     *
     * @param name of the ability
     */

    public void deleteByName(String name) {
        abilityRepository.deleteByName(name);
    }

    /**
     * Remove all the abilities
     */

    public void deleteAll() {
        abilityRepository.deleteAll();
    }

    /**
     * Remove given Ability
     *
     * @param Ability need to be removed
     */

    public void delete(Abilities Ability) {
        abilityRepository.delete(Ability);
    }
}
