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

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = {"/profile/password-edit/{userId}"})
    public String passwordCategoryPage(Model model, @PathVariable long userId) {

        Optional<User> user =userRepository.findById(userId);
        User us = user.get();
        String oldPswd = us.getPassword();
        model.addAttribute("oldPassword", oldPswd);
        model.addAttribute("user", us);

        return "passwordEdit";
    }

    @PostMapping(value = {"/profile/password-edit/{userId}"})
    public String adminChangeCategory(Model model, @PathVariable long userId,
         @ModelAttribute("oldPassword") long oldPswd, @ModelAttribute("user") @Valid User user) throws IOException {

        if(user.getPassword())
        userRepository.save(user);

        return "redirect:/profile";
    }
}
