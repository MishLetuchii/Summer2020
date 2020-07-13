package pack.rest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pack.domain.Categories;
import pack.domain.Items;
import pack.domain.User;
import pack.repositories.CategoriesRepository;
import pack.repositories.ItemsRepository;
import pack.repositories.UsersRepository;

import java.util.List;

@Controller
public class AdminPanelController {

    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private ItemsRepository itemsRepository;
    @Autowired
    private UsersRepository usersRepository;



    @GetMapping(value = "/adm")
    public String main(Model model) {

      /*  List<Categories> categories = categoriesRepository.findAll();
        List<User> users = usersRepository.findAll();
        List<Items> items = itemsRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("items",items);
        model.addAttribute("users")*/
        return "admin-panel";
    }


}