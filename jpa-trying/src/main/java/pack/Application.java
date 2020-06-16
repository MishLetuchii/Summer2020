package pack;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        CatalogRepository repository = context.getBean(CatalogRepository.class);


        repository.save(new Catalog("Мониторы", "Каталог мониторов для пк"));
        repository.save(new Catalog("Устройства ввода", "Каталог устройств ввода"));
        repository.save(new Catalog("Аудиоаппаратура", "Каталог устройств для воспроизведения аудио"));
        repository.save(new Catalog("Системные блоки", "Каталог готовых системных блоков"));



        Iterable<Catalog> catalogs = repository.findAll();
        System.out.println("catalogs found with findAll():");
        System.out.println("-------------------------------");
        for (Catalog catalog : catalogs) {
            System.out.println(catalog);
        }
        System.out.println();


        Catalog catalog = repository.findById(1L).orElse(null);
        System.out.println("Catalog found with findById(1L):");
        System.out.println("--------------------------------");
        System.out.println(catalog);
        System.out.println();


        List<Catalog> bauers = repository.findByName("Аудиоаппаратура");
        System.out.println("Catalog found with findByName('Аудиоаппаратура'):");
        System.out.println("--------------------------------------------");
        for (Catalog bauer : bauers) {
            System.out.println(bauer);
        }

        context.close();
    }

}