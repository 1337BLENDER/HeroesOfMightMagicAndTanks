package hello;

import hello.CharactersAbilities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharactersAbilitiesRepository extends CrudRepository<CharactersAbilities, Integer> {
    Iterable<CharactersAbilities> findAllByCharacter(Characters character);
}
