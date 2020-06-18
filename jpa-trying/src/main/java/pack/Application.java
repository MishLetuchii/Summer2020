package pack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pack.domain.Categories;
import pack.domain.Items;
import pack.repositories.CategoriesRepository;
import pack.repositories.ItemsRepository;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        CategoriesRepository сategoriesRepository = context.getBean(CategoriesRepository.class);
        ItemsRepository itemsRepository = context.getBean(ItemsRepository.class);
        //Создаем несколько категорий
        Categories screens = new Categories("Мониторы", "Каталог мониторов для пк");
        Categories keyboards = new Categories("Устройства ввода", "Каталог устройств ввода (мыши, клавиатуры)");
        Categories audioSystems = new Categories("Аудиоаппаратура", "Каталог устройств для воспроизведения аудио");
        //Сохраняем созданные категории в бд
        сategoriesRepository.save(screens);
        сategoriesRepository.save(keyboards);
        сategoriesRepository.save(audioSystems);
        //Создаем объекты в категориях
        Items HP_19ka = new Items(4598, "HP 19ka [T3U81AA]", 50, 4399, "Диагональ 18.5 дюйма.Разрешение 1366x768 точек", screens);
        Items Nec_MultiSync = new Items(4599, "Nec MultiSync PA311D", 3, 257499, "Диагональ 31 дюйм.Разрешение 4096x2160 точек", screens);
        Items Aceline_ASP100 = new Items(4600, "Aceline ASP100", 45, 380, "Формат системы 2.0", audioSystems);
        Items Canton_Movie_365 = new Items(4601, "Canton Movie 365", 10, 66000, "Формат системы 5.1", audioSystems);
        Items Defender_HB_420 = new Items(4602, "Defender HB-420", 45, 300, "Мембранная клавиатура Defender HB-420", keyboards);
        Items Logitech_G915 = new Items(4603, " Logitech G915", 18, 18499, "Механическая клавиатура  Logitech G915",keyboards);
        //сохраняем объекты в бд
        itemsRepository.save(HP_19ka);
        itemsRepository.save(Nec_MultiSync);
        itemsRepository.save(Aceline_ASP100);
        itemsRepository.save(Canton_Movie_365);
        itemsRepository.save(Defender_HB_420);
        itemsRepository.save(Logitech_G915);

        context.close();
    }

}