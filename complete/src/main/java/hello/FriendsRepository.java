package hello;

import hello.Friends;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsRepository extends CrudRepository<Friends, Integer> {
    public Iterable<Friends> findAllByUser1(Users user);
}
