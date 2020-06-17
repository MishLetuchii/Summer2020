package pack;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository<Categories, Long> {

    List<Categories> findByName(String name);
}