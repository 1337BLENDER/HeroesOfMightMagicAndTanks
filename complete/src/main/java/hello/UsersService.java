package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UsersService {

    private UserRepository userRepository;

    @Autowired
    public UsersService (UserRepository userRepository){this.userRepository=userRepository;}
    public UsersService(){}

    /**Find and return one user with given id
     * @param id of required user
     * @return founded user
     */

    public Users getById (int id){return userRepository.findOne(id);}

    /**
     * Find and return one user with given nick
     * @param nick of required user
     * @return founded user
     */

    public Users getByNick (String nick){return userRepository.findByNick(nick);}

    /**
     * Find and return all the users
     * @return users
     */

    public Iterable<Users> getAll(){return userRepository.findAll();}

    /**
     *Save user if it's new or update if it's already exists
     * @param user need to be saved
     * @return saved user
     */

    public Users saveOrUpdate(Users user){return userRepository.save(user);}

    /**
     * Remove user with given id
     * @param id of the user
     */

    public void deleteById(int id){userRepository.delete(id);}

    /**
     * Remove user with given nick
     * @param nick of the user
     */

    public void deleteByNick(String nick){userRepository.deleteByNick(nick);}

    /**
     * Remove all the users 
     */
    
    public void deleteAll(){userRepository.deleteAll();}

    /**
     * Remove given user
     * @param user need to be removed
     */
    
    public void delete(Users user){userRepository.delete(user);}
}
