package hello;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
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

    public Users getById (int id){
        Users user = userRepository.findOne(id);
        Hibernate.initialize(user.getArmy());
        Hibernate.initialize(user.getArmy().getUnits());
        Hibernate.initialize(user.getCharacter());
        Hibernate.initialize(user.getCharacter().getAbilities());
        Hibernate.initialize(user.getCity());
        Hibernate.initialize(user.getCity().getBuildings());
        Hibernate.initialize(user.getFriends());
        return user;
    }

    public Leader[] getLeaderboard(int limit){
        Leader[] leaders=new Leader[limit];
        Leader tempLeader;
        int i=0;
        loop:
        for(Users user:userRepository.findAllByOrderByWinrate()){
            Hibernate.initialize(user.getArmy());
            Hibernate.initialize(user.getArmy().getUnits());
            Hibernate.initialize(user.getCharacter());
            Hibernate.initialize(user.getCharacter().getAbilities());
            Hibernate.initialize(user.getCity());
            Hibernate.initialize(user.getCity().getBuildings());
            Hibernate.initialize(user.getFriends());
            tempLeader=new Leader(user.getNick(),user.getWinrate());
            leaders[i]=tempLeader;
            i++;
            if(i==limit){
                break loop;
            }
        }
        return leaders;
    }

    /**
     * Find and return one user with given nick
     * @param nick of required user
     * @return founded user
     */

    public Users getByNick (String nick){
        Users user = userRepository.findByNick(nick);
        Hibernate.initialize(user.getArmy());
        Hibernate.initialize(user.getArmy().getUnits());
        Hibernate.initialize(user.getCharacter());
        Hibernate.initialize(user.getCharacter().getAbilities());
        Hibernate.initialize(user.getCity());
        Hibernate.initialize(user.getCity().getBuildings());
        Hibernate.initialize(user.getFriends());
        return user;
    }

    /**
     * Find and return all the users
     * @return users
     */

    public Iterable<Users> getAll(){
        Iterable<Users> users = userRepository.findAll();
        for(Users user: users){
            Hibernate.initialize(user.getArmy());
            Hibernate.initialize(user.getArmy().getUnits());
            Hibernate.initialize(user.getCharacter());
            Hibernate.initialize(user.getCharacter().getAbilities());
            Hibernate.initialize(user.getCity());
            Hibernate.initialize(user.getCity().getBuildings());
            Hibernate.initialize(user.getFriends());
        }
        return users;
    }

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
