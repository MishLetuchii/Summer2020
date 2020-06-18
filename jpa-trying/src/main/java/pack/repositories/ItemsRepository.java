package pack.repositories;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import pack.domain.Items;

public interface ItemsRepository extends CrudRepository<Items, Long> {

    List<Items> findByName(String name);// поиск по названию предмета

    Items findById(long id);//поиск по id предмета

    void deleteById(long id); //удаление по id предмета
}