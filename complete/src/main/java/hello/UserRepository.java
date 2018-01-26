package hello;

import hello.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users,Integer> {
    Users findByNick(String nick);
    void deleteByNick(String nick);
    Iterable<Users> findAllByOrderByWinrateDesc();
}
