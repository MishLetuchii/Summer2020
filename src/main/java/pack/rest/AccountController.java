package pack.rest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
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
    public String profilePage(Model model, Authentication authentication) {


        if (authentication != null) {
            User user = userRepository.findByUserName(authentication.getName()).get();
            model.addAttribute("user", user);
        }

        return "profile";
    }

    @GetMapping(value = {"/profile/password-edit"})
    public String passwordCategoryPage(Model model) {

        User user=new User();
        model.addAttribute("user", user);

        return "passwordEdit";
    }

    @PostMapping(value = {"/profile/password-edit"})
    public String ChangePassword(Model model,Authentication authentication,
                                      @ModelAttribute("user") @Valid User us, BindingResult bindingResult) throws IOException {


        if (!us.getPassword().equals(us.getCheckPassword())) {
            bindingResult.rejectValue("password", "error.password", "Пароли не совпадают!");}

            if (bindingResult.hasErrors()) {
                return "redirect:/profile/password-edit";
        } else {
            User user = userRepository.findByUserName(authentication.getName()).get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(us.getPassword()));
            user.setCheckPassword(passwordEncoder.encode(us.getCheckPassword()));
            userRepository.save(user);

            return "redirect:/profile";
        }
    }
}

