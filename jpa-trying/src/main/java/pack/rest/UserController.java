package pack.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pack.domain.Categories;
import pack.repositories.CategoriesRepository;
import pack.repositories.ItemsRepository;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private ItemsRepository itemsRepository;

    @GetMapping(value = "/main")
    public String main(Model model) {

        List<Categories> categories = categoriesRepository.findAll();

        model.addAttribute("categories", categories);
        return "main";
    }
}
