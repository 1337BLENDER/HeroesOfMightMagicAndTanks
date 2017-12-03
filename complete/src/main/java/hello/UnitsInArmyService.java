package hello;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class UnitsInArmyService {
    private UnitsInArmyRepository unitsInArmyRepository;

    @Autowired
    public UnitsInArmyService (UnitsInArmyRepository unitsInArmyRepository){this.unitsInArmyRepository=unitsInArmyRepository;}
    public UnitsInArmyService(){}


    /**Find and return one unitsInArmy entity with given id
     * @param id of required unitsInArmy entity
     * @return founded unitsInArmy entity
     */
    
    public UnitsInArmy getById (int id){
        UnitsInArmy unitsInArmy=unitsInArmyRepository.findOne(id);
        Hibernate.initialize(unitsInArmy.getArmy());
        Hibernate.initialize(unitsInArmy.getUnit());
        return unitsInArmy;
    }

    /**
     * Find and return all the unitsInArmy entities
     * @return unitsInArmy entities
     */

    public Iterable<UnitsInArmy> getAll(){
        Iterable<UnitsInArmy> unitsInArmies=unitsInArmyRepository.findAll();
        for(UnitsInArmy unitsInArmy:unitsInArmies){
            Hibernate.initialize(unitsInArmy.getUnit());
            Hibernate.initialize(unitsInArmy.getArmy());
        }
        return unitsInArmies;
    }

    /**
     * Find and return all buildingInCities entities with given army
     * @param army need to be found
     * @return buildingInCities entities
     */

    public Collection<UnitsInArmy> getAllByArmy(Army army){
        Collection<UnitsInArmy> unitsInArmies=new ArrayList<>();
        for(UnitsInArmy unitsInArmy:unitsInArmyRepository.findAllByArmy(army)){
            unitsInArmies.add(unitsInArmy);
        }
        return unitsInArmies;
    }

    /**
     *Save unitsInArmy entity if it's new or update if it's already exists
     * @param unitsInArmy entity need to be saved
     * @return saved unitsInArmy entity
     */

    public UnitsInArmy saveOrUpdate(UnitsInArmy unitsInArmy){return unitsInArmyRepository.save(unitsInArmy);}

    /**
     * Remove unitsInArmy entity with given id
     * @param id of the unitsInArmy entity
     */
    
    public void deleteById(int id){unitsInArmyRepository.delete(id);}

    /**
     * Remove all the unitsInArmy entities 
     */

    public void deleteAll(){unitsInArmyRepository.deleteAll();}

    /**
     * Remove given unitsInArmy entity
     * @param unitsInArmy entity need to be removed
     */

    public void delete(UnitsInArmy unitsInArmy){unitsInArmyRepository.delete(unitsInArmy);}
}
