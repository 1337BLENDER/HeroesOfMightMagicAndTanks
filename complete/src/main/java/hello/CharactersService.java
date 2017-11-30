package hello;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CharactersService {
    private CharacterRepository characterRepository;

    @Autowired
    public CharactersService(CharacterRepository characterRepository){this.characterRepository=characterRepository;}
    public CharactersService(){}


    /**Find and return one character with given id
     * @param id of required character
     * @return founded character
     */
    
    public Characters getById (int id){return characterRepository.findOne(id);}

    /**
     * Find and return one character with given name
     * @param name of required character
     * @return founded character
     */

    public Characters getByName (String name){return characterRepository.findByName(name);}

    /**
     * Find and return all the characters
     * @return characters
     */

    public Iterable<Characters> getAll(){return characterRepository.findAll();}

    /**
     *Save character if it's new or update if it's already exists
     * @param character need to be saved
     * @return saved character
     */

    public Characters saveOrUpdate(Characters character){return characterRepository.save(character);}

    /**
     * Remove character with given id
     * @param id of the character
     */

    public void deleteById(int id){characterRepository.delete(id);}

    /**
     * Remove character with given name
     * @param name of the character
     */

    public void deleteByName(String name){characterRepository.deleteByName(name);}

    /**
     * Remove all the characters 
     */
    
    public void deleteAll(){characterRepository.deleteAll();}

    /**
     * Remove given character
     * @param character need to be removed
     */
    
    public void delete(Characters character){characterRepository.delete(character);}
}
