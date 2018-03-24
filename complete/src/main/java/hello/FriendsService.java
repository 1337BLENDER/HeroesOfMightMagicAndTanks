package hello;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendsService {
    private FriendsRepository friendRepository;

    @Autowired
    public FriendsService(FriendsRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public FriendsService() {
    }

    private void init(Friends friend) {
        Hibernate.initialize(friend.getUser1());
        Hibernate.initialize(friend.getUser2());
        //Hibernate.initialize(friend.getUser1().getCity());
        //Hibernate.initialize(friend.getUser2().getCity());
        Hibernate.initialize(friend.getUser1().getCharacter());
        Hibernate.initialize(friend.getUser2().getCharacter());
        Hibernate.initialize(friend.getUser1().getArmy());
        Hibernate.initialize(friend.getUser2().getArmy());
        //Hibernate.initialize(friend.getUser1().getCity().getBuildings());
        //Hibernate.initialize(friend.getUser2().getCity().getBuildings());
        Hibernate.initialize(friend.getUser1().getArmy().getUnits());
        Hibernate.initialize(friend.getUser2().getArmy().getUnits());
        Hibernate.initialize(friend.getUser1().getCharacter().getAbilities());
        Hibernate.initialize(friend.getUser2().getCharacter().getAbilities());
//        Hibernate.initialize(friend.getUser1().getRoles());
        //      Hibernate.initialize(friend.getUser2().getRoles());
        //Hibernate.initialize(friend.getUser1().getAppUser());
        //Hibernate.initialize(friend.getUser2().getAppUser());
    }

    /**
     * Find and return one friends entity with given id
     *
     * @param id of required friends entity
     * @return founded friends entity
     */
    public Friends getById(int id) {
        Friends friends = friendRepository.findOne(id);
        if (friends != null) {
            init(friends);
        }
        return friendRepository.findOne(id);
    }

    /**
     * Find and return all the friends entities
     *
     * @return friends entities
     */

    public Iterable<Friends> getAll() {
        Iterable<Friends> friends = friendRepository.findAll();
        for (Friends friend : friends) {
            if (friend != null) {
                init(friend);
            }
        }
        return friends;
    }

    /**
     * Find and return all buildingInCities entities with given user
     *
     * @param user need to be found
     * @return buildingInCities entities
     */

    public Iterable<Friends> getAllByUser1(String user) {
        Iterable<Friends> friends = friendRepository.findAllByUser1_Nick(user);
        for (Friends friend : friends) {
            if (friend != null) {
                init(friend);
            }
        }
        return friends;
    }

    public Friends getByNicks(String nick1,String nick2){
        return friendRepository.findFirstByUser1_NickAndUser2_Nick(nick1,nick2);
    }

    /**
     * Save friends entity if it's new or update if it's already exists
     *
     * @param friend need to be saved
     * @return saved friends entity
     */

    public Friends saveOrUpdate(Friends friend) {
        return friendRepository.save(friend);
    }

    /**
     * Remove friends entity with given id
     *
     * @param id of the friends entity
     */

    public void deleteById(int id) {
        friendRepository.delete(id);
    }

    public void deleteByNicks(String nick1,String nick2){
        friendRepository.deleteByUser1_NickAndUser2_Nick(nick1,nick2);
    }

    /**
     * Remove all the friends entities
     */

    public void deleteAll() {
        friendRepository.deleteAll();
    }

    /**
     * Remove given friends entity
     *
     * @param friend need to be removed
     */

    public void delete(Friends friend) {
        friendRepository.delete(friend);
    }
}
