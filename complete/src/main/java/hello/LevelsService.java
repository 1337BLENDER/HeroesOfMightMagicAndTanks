package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LevelsService {
    private LevelRepository levelRepository;

    @Autowired
    public LevelsService(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    public LevelsService() {
    }

    /**
     * Find and return level entity with given level
     *
     * @param level of the level entity
     * @return found level entity
     */

    public Levels getByLevel(int level) {
        return levelRepository.findOne(level);
    }

    /**
     * Find and return all the levels
     *
     * @return levels
     */

    public Iterable<Levels> getAll() {
        return levelRepository.findAll();
    }

    /**
     * Save level if it's new or update if it's already exists
     *
     * @param level need to be saved
     * @return saved level
     */

    public Levels saveOrUpdate(Levels level) {
        return levelRepository.save(level);
    }

    /**
     * Remove level entity with given level
     *
     * @param level of the level entity
     */

    public void deleteByLevel(int level) {
        levelRepository.delete(level);
    }

    /**
     * Remove all the levels
     */

    public void deleteAll() {
        levelRepository.deleteAll();
    }

    /**
     * Remove given level
     *
     * @param level need to be removed
     */

    public void delete(Levels level) {
        levelRepository.delete(level);
    }
}
