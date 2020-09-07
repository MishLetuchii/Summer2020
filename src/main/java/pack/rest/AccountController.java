package pack.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import pack.DTOs.UserDTO;
import pack.domain.User;
import org.springframework.ui.Model;
import pack.repositories.UsersRepository;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class AccountController {



    private UsersRepository userRepository;
    @Autowired
    public AccountController(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/profile")
    public String profilePage(Model model, Authentication authentication) {

        if (authentication != null) {
            User user = userRepository.findByUserName(authentication.getName()).get();
            if (user != null){
                UserDTO userDTO = new UserDTO();
                userDTO.setUserName(user.getUsername());
                model.addAttribute("user", userDTO);
                return "profile";}
            else return "redirect:/main";
        }
        else return "redirect:/main";
    }

    @GetMapping(value = {"/profile/password-edit"})
    public String changePasswordPage(Model model) {

        User user=new User();
        model.addAttribute("user", user);

        return "passwordEdit";
    }

    @PostMapping(value = {"/profile/password-edit"})
    public String changePassword(Model model,Authentication authentication,
                                      @ModelAttribute("user") @Valid User us, BindingResult bindingResult) throws IOException {
        if (authentication != null) {

        if (!us.getPassword().equals(us.getCheckPassword())) {
            bindingResult.rejectValue("password", "error.password", "Пароли не совпадают!");}

            if (bindingResult.hasErrors()) {
                return "redirect:/profile/password-edit";
        } else {
            User user = userRepository.findByUserName(authentication.getName()).get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(us.getPassword()));
            userRepository.save(user);

            return "redirect:/profile";
        }
        }
        else return "redirect:/main";
    }
}

