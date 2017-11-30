package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BuildingsService {
    private BuildingRepository buildingRepository;

    @Autowired
    public BuildingsService(BuildingRepository buildingRepository){this.buildingRepository=buildingRepository;}
    public BuildingsService(){}

    /**
     * Find and return one building with given id
     * @param id of building
     * @return building
     */

    public Buildings getById (int id){return buildingRepository.findOne(id);}

    /**
     * Find and return one building wiht given name
     * @param name of the building
     * @return building
     */

    public Buildings getByName (String name){return buildingRepository.findByName(name);}

    /**
     * Find and return all the buildings
     * @return Buildings
     */

    public Iterable<Buildings> getAll(){return buildingRepository.findAll();}

    /**
     * save building if it's new or update if it exists
     * @param Building need to be saved
     * @return saved building
     */

    public Buildings saveOrUpdate(Buildings Building){return buildingRepository.save(Building);}

    /**
     * remove building with given id
     * @param id of the building
     */

    public void deleteById(int id){buildingRepository.delete(id);}

    /**
     * Remove building with given name
     * @param name of the building
     */

    public void deleteByName(String name){buildingRepository.deleteByName(name);}

    /**
     * Remove all the buildings
     */

    public void deleteAll(){buildingRepository.deleteAll();}

    /**
     * Remove given building
     * @param Building need to be removed
     */

    public void delete(Buildings Building){buildingRepository.delete(Building);}
}
