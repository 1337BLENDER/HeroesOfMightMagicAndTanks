package hello;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AppUser {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private @Id
    @GeneratedValue
    Long id;

    @Column(unique = true)
    private String name;

    private String password;

    private String[] roles;

    private AppUser() {
    }

    public AppUser(String name, String password, String... roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String[] getRoles() {
        return roles;
    }
}
