package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendsService {
    private FriendsRepository friendRepository;

    @Autowired
    public FriendsService(FriendsRepository friendRepository){this.friendRepository=friendRepository;}
    public FriendsService(){}


    /**Find and return one friends entity with given id
     * @param id of required friends entity
     * @return founded friends entity
     */
    
    public Friends getById (int id){return friendRepository.findOne(id);}

    /**
     * Find and return all the friends entities
     * @return friends entities
     */
    
    public Iterable<Friends> getAll(){return friendRepository.findAll();}

    /**
     * Find and return all buildingInCities entities with given user
     * @param user need to be found
     * @return buildingInCities entities
     */
    
    public Iterable<Friends> getAllByUser1(Users user){return friendRepository.findAllByUser1(user);}

    /**
     *Save friends entity if it's new or update if it's already exists
     * @param friend need to be saved
     * @return saved friends entity
     */
    
    public Friends saveOrUpdate(Friends friend){return friendRepository.save(friend);}

    /**
     * Remove friends entity with given id
     * @param id of the friends entity
     */

    public void deleteById(int id){friendRepository.delete(id);}

    /**
     * Remove all the friends entities 
     */

    public void deleteAll(){friendRepository.deleteAll();}

    /**
     * Remove given friends entity
     * @param friend need to be removed
     */
    
    public void delete(Friends friend){friendRepository.delete(friend);}
}
