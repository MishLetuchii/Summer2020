package pack.repositories;

import org.springframework.data.repository.CrudRepository;
import pack.domain.Position;

public interface PositionRepository extends CrudRepository<Position, Long> {
}
