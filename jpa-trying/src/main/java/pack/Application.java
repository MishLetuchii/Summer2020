package pack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pack.domain.User;
import pack.repositories.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;


@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        UsersRepository usersRepository = context.getBean(UsersRepository.class);

         Optional<User> us = usersRepository.findById(1092L);
         User user = us.get();
         user.setRoles("ROLE_ADMIN");
         usersRepository.save(user);

        System.out.println( usersRepository.findAll());

    }

}