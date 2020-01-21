package ludopia.config;

import ludopia.objects.users.service.UserService;
import ludopia.objects.users.LudopiaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/registration","/addGame","/css/**", "/js/**", "/infos/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
        http.cors().and().csrf().disable();

    }

    private static PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        if (passwordEncoder == null) {
            passwordEncoder = new BCryptPasswordEncoder();
        }
        return passwordEncoder;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                LudopiaUser ludopiaUser = userService.getUserByUsername(username);
                if(ludopiaUser != null){
                    return User.builder().username(ludopiaUser.getUsername()).password(ludopiaUser.getPassword()).authorities("ROLE_USER").build();
                }
                throw new BadCredentialsException("No such user");
            }
        };
    }

    public void setTrainerService(UserService userService) {
        this.userService = userService;
    }

    public static PasswordEncoder getPasswordEncoder(){
        return passwordEncoder;
    }
}
