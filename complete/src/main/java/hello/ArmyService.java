package hello;

import org.hibernate.Hibernate;
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
    public ArmyService(ArmyRepository armyRepository, UnitsInArmyService unitsInArmyService) {
        this.armyRepository = armyRepository;
        this.unitsInArmyService = unitsInArmyService;
    }

    public ArmyService() {
    }

    /**
     * Find and return army with given id
     *
     * @param id of army
     * @return army
     */

    public Army getById(int id) {
        Army army = armyRepository.findOne(id);
        Hibernate.initialize(army.getUnits());
        return army;
    }

    /**
     * Find and return all the armies
     *
     * @return armies
     */

    public Iterable<Army> getAll() {
        Iterable<Army> armies = armyRepository.findAll();
        for (Army army : armies) {
            Hibernate.initialize(army.getUnits());
        }
        return armies;
    }

    /**
     * Save the army if it's new or update if it exists
     *
     * @param army need to be saved
     * @return saved army
     */

    public Army saveOrUpdate(Army army) {
        return armyRepository.save(army);
    }

    /**
     * delete arny with given id
     *
     * @param id of army
     */

    public void deleteById(int id) {
        armyRepository.delete(id);
    }

    /**
     * delete all the armies
     */

    public void deleteAll() {
        armyRepository.deleteAll();
    }

    /**
     * delete given army
     *
     * @param army need to be deleted
     */

    public void delete(Army army) {
        armyRepository.delete(army);
    }

    public Army getLastById() {
        Army army = armyRepository.findFirstByPowerOrderByIdDesc(0);
        Hibernate.initialize(army.getUnits());
        return army;
    }
}
