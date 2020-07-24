package pack.repositories;

import org.springframework.data.repository.CrudRepository;
import pack.domain.Basket;

public interface BasketRepository extends CrudRepository<Basket, Long> {

}
