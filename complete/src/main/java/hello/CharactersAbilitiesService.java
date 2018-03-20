package hello;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CharactersAbilitiesService {
    private CharactersAbilitiesRepository charactersAbilitiesRepository;

    @Autowired
    public CharactersAbilitiesService(CharactersAbilitiesRepository charactersAbilitiesRepository) {
        this.charactersAbilitiesRepository = charactersAbilitiesRepository;
    }

    public CharactersAbilitiesService() {
    }

    /**
     * Find and return one charactersAbilities entity with given id
     *
     * @param id of required charactersAbilities entity
     * @return founded charactersAbilities entity
     */
    public CharactersAbilities getById(int id) {
        return charactersAbilitiesRepository.findOne(id);
    }

    /**
     * Find and return all charactersAbilities entities with given character
     *
     * @param character need to be found
     * @return charactersAbilities entities
     */

    public Iterable<CharactersAbilities> getAllByCharacter(Characters character) {
        return charactersAbilitiesRepository.findAllByCharacter(character);
    }

    /**
     * Find and return all the charactersAbilities entities
     *
     * @return charactersAbilities entities
     */

    public Iterable<CharactersAbilities> getAll() {
        return charactersAbilitiesRepository.findAll();
    }

    /**
     * Save charactersAbilities entity if it's new or update if it's already exists
     *
     * @param charactersAbilities entity need to be saved
     * @return saved charactersAbilities entity
     */

    public CharactersAbilities saveOrUpdate(CharactersAbilities charactersAbilities) {
        return charactersAbilitiesRepository.save(charactersAbilities);
    }

    /**
     * Remove charactersAbilities entity with given id
     *
     * @param id of the charactersAbilities entity
     */

    public void deleteById(int id) {
        charactersAbilitiesRepository.delete(id);
    }

    /**
     * Remove all the charactersAbilities entities
     */

    public void deleteAll() {
        charactersAbilitiesRepository.deleteAll();
    }

    /**
     * Remove given charactersAbilities entity
     *
     * @param charactersAbilities entity need to be removed
     */

    public void delete(CharactersAbilities charactersAbilities) {
        charactersAbilitiesRepository.delete(charactersAbilities);
    }
}
