package pack.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pack.domain.User;
import pack.repositories.UsersRepository;

import java.util.Optional;

@Service
class UserDetailsServiceImpl implements UserDetailsService
        {

            private UsersRepository userRepository;
            @Autowired
            public void setUserRepository(UsersRepository userRepository) {
                this.userRepository = userRepository;
            }

            @Override
            public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
                Optional<User> user = userRepository.findByUserName(userName);

                user.orElseThrow(() -> new UsernameNotFoundException(userName + " не найден!"));
                return user.map(UserDetailsImpl::new).get();
            }
        }