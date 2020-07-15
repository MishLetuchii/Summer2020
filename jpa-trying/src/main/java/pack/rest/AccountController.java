package pack.rest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import pack.domain.Categories;
import pack.domain.User;
import pack.domain.Items;
import pack.repositories.CategoriesRepository;
import pack.repositories.ItemsRepository;
import org.springframework.ui.Model;
import pack.repositories.UsersRepository;

import java.io.IOException;
import java.util.List;

@Controller
public class AccountController {


    @Autowired
    private UsersRepository userRepository;

    @GetMapping(value = "/profile")
    public String profilePage(Model model,  Authentication authentication) {


        if (authentication != null) {
            User user = userRepository.findByUserName(authentication.getName()).get();
            model.addAttribute("user", user);
        }

        return "profile";
    }
}
