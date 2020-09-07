package pack.rest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pack.DTOs.CategoriesDTO;
import pack.DTOs.ItemsDTO;
import pack.domain.Basket;
import pack.domain.Categories;
import pack.domain.Items;
import pack.domain.User;
import pack.repositories.BasketRepository;
import pack.repositories.CategoriesRepository;
import pack.repositories.ItemsRepository;
import org.springframework.ui.Model;
import pack.repositories.UsersRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {


    private CategoriesRepository categoriesRepository;
    private ItemsRepository itemsRepository;
    private UsersRepository userRepository;
    private BasketRepository basketRepository;

    @Autowired
    public UserController(CategoriesRepository categoriesRepository, ItemsRepository itemsRepository, UsersRepository userRepository, BasketRepository basketRepository) {
        this.categoriesRepository = categoriesRepository;
        this.itemsRepository = itemsRepository;
        this.userRepository = userRepository;
        this.basketRepository = basketRepository;
    }

    @GetMapping(value = "/main")
    public String main(Model model) {

        CategoriesDTO helpDTO=new CategoriesDTO();
        List<CategoriesDTO> categoriesDTO=new ArrayList<CategoriesDTO>();
        List<Categories> categories = categoriesRepository.findAll();

        for (Categories category : categories) {
            byte[] image = category.getImage();
            category.setImageString(Base64.encodeBase64String(image));
            helpDTO.setName(category.getName());
            helpDTO.setId(category.getId());
            helpDTO.setImageString(category.getImageString());
            categoriesDTO.add(new CategoriesDTO(helpDTO));
        }
        model.addAttribute("categories", categoriesDTO);

        return "main";
    }

    @GetMapping(value = "/main/categories/{CtgId}")
    public String userCategoriesPage(Model model, @PathVariable long CtgId) {

        ItemsDTO itemDTO = new ItemsDTO();
        List<ItemsDTO> itemsDTOS = new ArrayList<ItemsDTO>();

        Categories categ = categoriesRepository.findById(CtgId);
        CategoriesDTO categDTO = new CategoriesDTO();
        categDTO.setName(categ.getName());
        categDTO.setId(categ.getId());
        categDTO.setDescription(categ.getDescription());

        List<Items> items= itemsRepository.findByCategory(categ);
        for (Items item : items) {
            byte[] image = item.getImage();
            item.setImageString(Base64.encodeBase64String(image));
            itemDTO.setImageString(item.getImageString());
            itemDTO.setName(item.getName());
            itemDTO.setId(item.getId());
            itemDTO.setPrice(item.getPrice());
            itemsDTOS.add(new ItemsDTO(itemDTO));
        }
        model.addAttribute("items", itemsDTOS);
        model.addAttribute("category",categDTO);


        return "user-main-ctg";
    }


    @GetMapping(value = "/main/items/{ItemId}")
    public String userItemPage(Model model,@PathVariable long ItemId) {

        Items item= itemsRepository.findById(ItemId);
        byte[] image = item.getImage();
        item.setImageString(Base64.encodeBase64String(image));
        ItemsDTO itemDTO = new ItemsDTO(item);
        model.addAttribute("item", itemDTO);

        return "selected-items";
    }


}
