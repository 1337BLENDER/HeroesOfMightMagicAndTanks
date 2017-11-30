package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class ArmyService {
    private ArmyRepository armyRepository;
    private UnitsInArmyService unitsInArmyService;

    @Autowired
    public ArmyService (ArmyRepository armyRepository, UnitsInArmyService unitsInArmyService){
        this.armyRepository=armyRepository;
        this.unitsInArmyService = unitsInArmyService;
    }
    public ArmyService(){}

    private Army setUnits(Army army){
        army.setUnits(unitsInArmyService.getAllByArmy(army));
        army.calculatePower();
        return army;
    }

    /**
     * Find and return army with given id
     * @param id of army
     * @return army
     */

    public Army getById (int id){return setUnits(armyRepository.findOne(id));}

    /**
     * Find and return all the armies
     * @return armies
     */

    public Collection<Army> getAll(){
        Collection<Army> armies=new ArrayList<>();
        for(Army army:armyRepository.findAll()){
            armies.add(setUnits(army));
        }
        return armies;
    }

    /**
     * Save the army if it's new or update if it exists
     * @param army need to be saved
     * @return saved army
     */

    public Army saveOrUpdate(Army army){return setUnits(armyRepository.save(army));}

    /**
     * delete arny with given id
     * @param id of army
     */

    public void deleteById(int id){armyRepository.delete(id);}

    /**
     * delete all the armies
     */

    public void deleteAll(){armyRepository.deleteAll();}

    /**
     * delete given army
     * @param army need to be deleted
     */

    public void delete(Army army){armyRepository.delete(army);}

    public Army getFirstById(int id){
        return setUnits( armyRepository.findFirstById(id));
    }
}
