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
    private final AppUserRepository repository;

    @Autowired
    SpringDataJPAUserDetailsService(AppUserRepository repository){
        this.repository=repository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException{
        AppUser user = this.repository.findByName(name);
        if(user == null)
            throw new UsernameNotFoundException(name);
        return new User(user.getName(),user.getPassword(), AuthorityUtils.createAuthorityList(user.getRoles()));
    }
}