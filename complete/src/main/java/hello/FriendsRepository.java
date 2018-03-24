package hello;

import hello.Friends;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsRepository extends CrudRepository<Friends, Integer> {
    public Iterable<Friends> findAllByUser1_Nick(String nick);
    public void deleteByUser1_NickAndUser2_Nick(String nick1,String nick2);
    public Friends findFirstByUser1_NickAndUser2_Nick(String nick1,String nick2);
}
