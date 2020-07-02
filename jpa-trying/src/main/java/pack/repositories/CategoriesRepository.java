package pack.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import pack.domain.Categories;

public interface CategoriesRepository extends CrudRepository<Categories, Long> {

    List<Categories> findByName(String name);
    Categories findById(long CtgId);
    List<Categories> findAll();
    void  deleteById(long CtgId);

}