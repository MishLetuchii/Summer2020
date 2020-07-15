package pack.repositories;

import org.springframework.data.repository.CrudRepository;
import pack.domain.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserName(String userName);
    List<User> findByRoles(String roles);

    List<User> findAll();
    void deleteById(long id);
    void deleteAll();
}