package pack.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pack.DTOs.UserDTO;
import pack.domain.User;
import pack.repositories.CategoriesRepository;
import pack.repositories.ItemsRepository;
import pack.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminUsersController {

    private CategoriesRepository categoriesRepository;
    private ItemsRepository itemsRepository;
    private UsersRepository usersRepository;

    @Autowired
    public AdminUsersController(CategoriesRepository categoriesRepository, ItemsRepository itemsRepository, UsersRepository usersRepository) {
        this.categoriesRepository = categoriesRepository;
        this.itemsRepository = itemsRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping(value = "/adm/users")
    public String adminUserPage(Model model) {

        List<User> users = usersRepository.findAll();//findByRoles("ROLE_USER");
        List<UserDTO> userDTOS = new ArrayList<UserDTO>();
        UserDTO userDTO=new UserDTO();

        for (User user : users) {
            userDTO.setUserName(user.getUsername());
            userDTO.setId(user.getId());
            userDTOS.add(new UserDTO(userDTO));
        }

        model.addAttribute("users", userDTOS);

        return "admin-user-db";
    }
    @RequestMapping(value = "/adm/users/delete/{userId}")
    public String adminDeleteItem(Model model, RedirectAttributes redirectAttributes, @PathVariable long userId) {

        usersRepository.deleteById(userId);

        return "redirect:/adm/users";
    }
}
