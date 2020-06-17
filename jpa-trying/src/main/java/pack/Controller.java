package pack;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    @RequestMapping("/categories")
    public Categories categories(@RequestParam(value = "name", required = false, defaultValue = "Мониторы") String name) {
        return new Categories(name, "");
    }
}