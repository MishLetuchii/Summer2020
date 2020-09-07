package pack.rest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPanelController {

    @GetMapping(value = "/adm")
    public String main(Model model) {

        return "admin-panel";
    }
}