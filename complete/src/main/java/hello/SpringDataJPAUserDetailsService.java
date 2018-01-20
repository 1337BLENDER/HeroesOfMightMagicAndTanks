package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SpringDataJPAUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    @Autowired
    SpringDataJPAUserDetailsService(UserRepository repository){
        this.repository=repository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException{
        Users user = this.repository.findByNick(name);
        if(user == null)
            throw new UsernameNotFoundException("Username not found");
        return new User(user.getNick(),user.getPassword(), AuthorityUtils.createAuthorityList(user.getRoles()));
    }
}