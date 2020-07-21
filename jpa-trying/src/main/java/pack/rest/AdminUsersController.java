package pack.rest;


import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pack.domain.Items;
import pack.domain.User;
import pack.repositories.CategoriesRepository;
import pack.repositories.ItemsRepository;
import pack.repositories.UsersRepository;

import java.util.List;

@Controller
public class AdminUsersController {

    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private ItemsRepository itemsRepository;
    @Autowired
    private UsersRepository usersRepository;


    @GetMapping(value = "/adm/users")
    public String adminUserPage(Model model) {

        List<User> users = usersRepository.findAll();//findByRoles("ROLE_USER");

        model.addAttribute("users", users);

        return "admin-user-db";
    }
    @RequestMapping(value = "/adm/users/delete/{userId}")
    public String adminDeleteItem(Model model, RedirectAttributes redirectAttributes, @PathVariable long userId) {

        usersRepository.deleteById(userId);

        return "redirect:/adm/users";
    }
}
