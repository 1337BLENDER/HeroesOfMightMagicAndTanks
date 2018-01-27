package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SpringDataJPAUserDetailsService userDetailsService;

    /*@Autowired
    @Qualifier("dataSource")
    DataSource dataSource;*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(this.userDetailsService).passwordEncoder(AppUser.PASSWORD_ENCODER);
//        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("Select nick, password, active from users where nick = ?").authoritiesByUsernameQuery("select u.nick, r.role from users u inner join user_role ur on(ur.id=u.id) inner join role r on(ur.role_id=r.role_id) where u.nick=? ").passwordEncoder(Users.PASSWORD_ENCODER);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/service/**", "/css/style.css", "/js/**", "/", "/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error")
                .defaultSuccessUrl("/lk", true)
                .usernameParameter("nick")
                .passwordParameter("password")
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .logout()
                .logoutSuccessUrl("/");
    }
}