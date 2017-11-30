package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class RaceService {
    private RaceRepository raceRepository;

    @Autowired
    public RaceService(RaceRepository raceRepository){this.raceRepository=raceRepository;}
    public RaceService(){}

    /**Find and return one race with given id
     * @param id of required race
     * @return founded race
     */

    public Race getById (int id){return raceRepository.findOne(id);}

    /**Find and return one race with given name
     * @param name of required race
     * @return founded race
     */

    public Race getByName (String name){return raceRepository.findByName(name);}

    /**
     * Find and return all the races
     * @return races
     */

    public Iterable<Race> getAll(){return raceRepository.findAll();}

    /**
     *Save race if it's new or update if it's already exists
     * @param race need to be saved
     * @return saved race
     */

    public Race saveOrUpdate(Race race){return raceRepository.save(race);}

    /**
     * Remove race with given id
     * @param id of the race
     */

    public void deleteById(int id){raceRepository.delete(id);}

    /**
     * Remove race with given name
     * @param name of the race
     */

    public void deleteByName(String name){raceRepository.deleteByName(name);}

    /**
     * Remove all the races 
     */
    
    public void deleteAll(){raceRepository.deleteAll();}

    /**
     * Remove given race
     * @param race need to be removed
     */
    
    public void delete(Race race){raceRepository.delete(race);}
}
