package pack.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/","/main/**").permitAll()
                .antMatchers( "/adm/**").hasRole("ADMIN");
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
<<<<<<< Updated upstream:src/main/java/pack/configuration/WebSecurityConfig.java
                .logoutSuccessUrl("/")
=======
<<<<<<< HEAD:src/main/java/pack/configuration/WebSecurityConfig.java
                .logoutSuccessUrl("/main")
=======
                .logoutSuccessUrl("/")
>>>>>>> 2e0c524a2a074a84d1dcc3d0d39df61bc45268c0:jpa-trying/src/main/java/pack/configuration/WebSecurityConfig.java
>>>>>>> Stashed changes:jpa-trying/src/main/java/pack/configuration/WebSecurityConfig.java
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-error");

    }

}