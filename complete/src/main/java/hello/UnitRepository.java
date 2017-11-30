package hello;
import hello.Units;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UnitRepository extends CrudRepository<Units,Integer> {
    Units findByName(String name);
    void deleteByName(String name);
}
