package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CitiesService {
    private CityRepository cityRepository;

    @Autowired
    public CitiesService(CityRepository cityRepository){this.cityRepository=cityRepository;}
    public CitiesService(){}

    /**Find and return one city with given id
     * @param id of required city
     * @return founded city
     */

    public Cities getById (int id){return cityRepository.findOne(id);}

    /**
     * Find and return one city with given name
     * @param name of required city
     * @return founded city
     */

    public Cities getByName (String name){return cityRepository.findByName(name);}

    /**
     * Find and return all the cities
     * @return cities
     */

    public Iterable<Cities> getAll(){return cityRepository.findAll();}

    /**
     *Save city if it's new or update if it's already exists
     * @param city need to be saved
     * @return saved city
     */

    public Cities saveOrUpdate(Cities city){return cityRepository.save(city);}

    /**
     * Remove city with given id
     * @param id of the city
     */

    public void deleteById(int id){cityRepository.delete(id);}

    /**
     * Remove city with given name
     * @param name of the city
     */
    
    public void deleteByName(String name){cityRepository.deleteByName(name);}

    /**
     * Remove all the cities 
     */
    
    public void deleteAll(){cityRepository.deleteAll();}

    /**
     * Remove given city
     * @param city need to be removed
     */
    
    public void delete(Cities city){cityRepository.delete(city);}
}
