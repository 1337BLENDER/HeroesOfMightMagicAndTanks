package hello;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class UsersService {

    private UserRepository userRepository;
    private AppUserRepository appUserRepository;

    @Autowired
    public UsersService(UserRepository userRepository, AppUserRepository appUserRepository) {
        this.userRepository = userRepository;
        this.appUserRepository = appUserRepository;
    }

    public UsersService() {
    }

    private void init(Users user) {
        Hibernate.initialize(user.getArmy());
        Hibernate.initialize(user.getArmy().getUnits());
        Hibernate.initialize(user.getCharacter());
        Hibernate.initialize(user.getCharacter().getAbilities());
        Hibernate.initialize(user.getFriends());
        //Hibernate.initialize(user.getAppUser());
        //Hibernate.initialize(user.getCity());
        //Hibernate.initialize(user.getCity().getBuildings());
    }

    /**
     * Find and return one user with given id
     *
     * @param id of required user
     * @return founded user
     */

    public Users getById(int id) {
        Users user = userRepository.findOne(id);
        if (user != null) {
            init(user);
        }
        return user;
    }

    public Leader[] getLeaderboard(int limit) {
        List<Leader> leaders = new ArrayList<>();
        Leader tempLeader;
        int i = 0;
        loop:
        for (Users user : userRepository.findAllByOrderByWinrateDesc()) {
            init(user);
            tempLeader = new Leader(user.getNick(), user.getWinrate());
            leaders.add(tempLeader);
            i++;
            if (i == limit) {
                break loop;
            }
        }
        Leader[] leaderArray = new Leader[leaders.size()];
        i = 0;
        for (Leader leader : leaders) {
            leaderArray[i] = leader;
            i++;
        }
        return leaderArray;
    }

    /**
     * Find and return one user with given nick
     *
     * @param nick of required user
     * @return founded user
     */

    public Users getByNick(String nick) {
        Users user = userRepository.findByNick(nick);
        if (user != null) {
            init(user);
        }
        return user;
    }

    /**
     * Find and return all the users
     *
     * @return users
     */

    public Iterable<Users> getAll() {
        Iterable<Users> users = userRepository.findAll();
        for (Users user : users) {
            if (user != null) {
                init(user);
            }
        }
        return users;
    }

    /**
     * Save user if it's new or update if it's already exists
     *
     * @param user need to be saved
     * @return saved user
     */

    public Users save(Users user) {
        appUserRepository.save(new AppUser(user.getNick(),user.getPassword()));
        return userRepository.save(user);
    }

    public Users update(Users newUser){
        return userRepository.save(newUser);
    }

    /**
     * Remove user with given id
     *
     * @param id of the user
     */

    public void deleteById(int id) {
        userRepository.delete(id);
    }

    /**
     * Remove user with given nick
     *
     * @param nick of the user
     */

    public void deleteByNick(String nick) {
        userRepository.deleteByNick(nick);
    }

    /**
     * Remove all the users
     */

    public void deleteAll() {
        userRepository.deleteAll();
    }

    /**
     * Remove given user
     *
     * @param user need to be removed
     */

    public void delete(Users user) {
        userRepository.delete(user);
    }
}
