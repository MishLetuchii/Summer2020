package pack.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pack.repositories.UsersRepository;
import org.springframework.ui.Model;


@Controller
public class SecurityController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        return "login";
    }



}
