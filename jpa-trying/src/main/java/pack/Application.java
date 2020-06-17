package pack;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        CategoriesRepository repository = context.getBean(CategoriesRepository.class);


        repository.save(new Categories("Мониторы", "Каталог мониторов для пк"));
        repository.save(new Categories("Устройства ввода", "Каталог устройств ввода"));
        repository.save(new Categories("Аудиоаппаратура", "Каталог устройств для воспроизведения аудио"));
        repository.save(new Categories("Системные блоки", "Каталог готовых системных блоков"));



        Iterable<Categories> catalogs = repository.findAll();
        System.out.println("Cathegories found with findAll():");
        System.out.println("-------------------------------");
        for (Categories categories : catalogs) {
            System.out.println(categories);
        }
        System.out.println();


        Categories categories = repository.findById(1L).orElse(null);
        System.out.println("Cathegories found with findById(1L):");
        System.out.println("--------------------------------");
        System.out.println(categories);
        System.out.println();


        List<Categories> fbnabe = repository.findByName("Аудиоаппаратура");
        System.out.println("Cathegories found with findByName('Аудиоаппаратура'):");
        System.out.println("--------------------------------------------");
        for (Categories name : fbnabe) {
            System.out.println(name);
        }

        context.close();
    }

}