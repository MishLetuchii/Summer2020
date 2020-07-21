package pack.rest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import pack.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pack.repositories.UsersRepository;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;


@Controller
public class SecurityController {

    @Autowired
    private UsersRepository userRepository;

    @GetMapping(value = "/login")
    public String loginPage(Model model) {

        return "login";
    }

    @GetMapping(value = {"/registration"})
    public String registrationPage(Model model) {

        User user = new User();

        model.addAttribute("user", user);

        return "registration";
    }





    @PostMapping(value = {"/registration"})
    public String registration(Model model, @ModelAttribute("user") @Valid User user, BindingResult bindingResult) throws Exception {
    {
        List<User> userList = userRepository.findAll();

        if (!user.getPassword().equals(user.getCheckPassword())) {
            bindingResult.rejectValue("password", "error.password", "Пароли не совпадают!");
        }

        for (User curUser : userList) {
            if (user.getUserName().equals(curUser.getUserName())) {
                bindingResult.rejectValue("userName", "error.userName", "Пользователь с таким никнейном уже зарегистрирован!");
            }
        }

        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setActive(true);
            user.setRoles("ROLE_USER");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            return  "redirect:/main";
        }
        }
    }

}
