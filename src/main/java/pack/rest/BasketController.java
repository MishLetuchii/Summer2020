package pack.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pack.DTOs.BasketDTO;
import pack.domain.Basket;
import pack.domain.Items;
import pack.domain.Position;
import pack.domain.User;
import pack.repositories.BasketRepository;
import pack.repositories.ItemsRepository;
import pack.repositories.UsersRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BasketController {



    private UsersRepository userRepository;
    private BasketRepository basketRepository;
    private ItemsRepository itemsRepository;

    @Autowired
    public BasketController(UsersRepository userRepository, BasketRepository basketRepository, ItemsRepository itemsRepository) {
        this.userRepository = userRepository;
        this.basketRepository = basketRepository;
        this.itemsRepository = itemsRepository;
    }

    @GetMapping(value = "/basket")
    public String basketPage(Model model,
                             Authentication authentication) {
        if(authentication!= null)
        {
         User user = userRepository.findByUserName(authentication.getName()).get();
            if (user != null)
                {
                Basket basket = user.getBasket();
                model.addAttribute("basket", basket);
                return "basket";
                }
            else return "redirect:/main";
        }

        else return "redirect:/main";
    }

    @GetMapping(value = "/main/addToBasket/items/{ItemId}")
    public String addItemToBasketPage(Model model, RedirectAttributes redirectAttributes, @PathVariable long itemId) {
        redirectAttributes.addAttribute("itemId", itemId);
        return "redirect:/main/items/{itemId}";
    }

    @PostMapping(value = {"/main/addToBasket/items/{itemId}"})
    public String addItemToBasket(Model model, RedirectAttributes redirectAttributes, @PathVariable long itemId,
                                  Authentication authentication) throws IOException {

        Items item =itemsRepository.findById(itemId);

        User user = userRepository.findByUserName(authentication.getName()).get();
        user.getBasket().addItemToBasket(item);
        basketRepository.save(user.getBasket());
        redirectAttributes.addAttribute("itemId", item.getId());
        return "redirect:/main/items/{itemId}";

    }

    @GetMapping(value = "/basket/addInBasket/items/{ItemId}")
    public String addItemInBasketPage(Model model, @PathVariable long itemId) {
               return "redirect:/basket";
    }

    @PostMapping(value = {"/basket/addInBasket/items/{itemId}"})
    public String addItemInBasket(Model model, RedirectAttributes redirectAttributes, @PathVariable long itemId,
                                  Authentication authentication) throws IOException {

        Items item =itemsRepository.findById(itemId);
        User user = userRepository.findByUserName(authentication.getName()).get();
        user.getBasket().addItemToBasket(item);
        basketRepository.save(user.getBasket());
        redirectAttributes.addAttribute("itemId", item.getId());
        return "redirect:/basket";
    }
    @GetMapping(value = "/basket/removeFromBasket/items/{ItemId}")
    public String removeItemFromBasketPage(Model model,  @PathVariable long itemId) {

        return "redirect:/basket";
    }

    @PostMapping(value = {"/basket/removeFromBasket/items/{itemId}"})
    public String removeItemFromBasket(Model model,  @PathVariable long itemId,
                                  Authentication authentication) throws IOException {

        Items item =itemsRepository.findById(itemId);
        User user = userRepository.findByUserName(authentication.getName()).get();
        user.getBasket().removeItemFromBasket(item);
        Basket basket = user.getBasket();
        userRepository.save(user);
        basketRepository.save(basket);

        return "redirect:/basket";
    }

    @GetMapping(value = "/basket/clear")
    public String basketClearPage (Model model) {

        return "redirect:/basket";
    }

    @PostMapping(value = {"/basket/clear"})
    public String basketClear (Model model,
                                  Authentication authentication) throws IOException {

        User user = userRepository.findByUserName(authentication.getName()).get();
        user.getBasket().setBasket_items(new ArrayList<Position>());
        userRepository.save(user);
        basketRepository.save(user.getBasket());

        return "redirect:/basket";
    }
}
