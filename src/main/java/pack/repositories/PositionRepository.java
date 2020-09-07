package pack.repositories;

import org.springframework.data.repository.CrudRepository;
import pack.domain.Basket;
import pack.domain.Position;

public interface PositionRepository extends CrudRepository<Position, Long> {
    void deleteByBasket (Basket basket);
}
