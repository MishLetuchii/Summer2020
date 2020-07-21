package pack.rest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import pack.domain.Categories;
import pack.domain.Items;
import pack.repositories.CategoriesRepository;
import pack.repositories.ItemsRepository;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private ItemsRepository itemsRepository;

    @GetMapping(value = "/main")
    public String main(Model model) {

        List<Categories> categories = categoriesRepository.findAll();
        for (Categories category : categories) {
            byte[] image = category.getImage();
            category.setImageString(Base64.encodeBase64String(image));
        }
        model.addAttribute("categories", categories);

        return "main";
    }

    @GetMapping(value = "/main/categories/{CtgId}")
    public String userCategoriesPage(Model model, @PathVariable long CtgId) {

        Categories categ = categoriesRepository.findById(CtgId);
        List<Items> items= itemsRepository.findByCategory(categ);
        for (Items item : items) {
            byte[] image = item.getImage();
            item.setImageString(Base64.encodeBase64String(image));
        }
        model.addAttribute("items", items);
        model.addAttribute("category",categ);


        return "user-main-ctg";
    }


    @GetMapping(value = "/main/items/{ItemId}")
    public String userItemPage(Model model,@PathVariable long ItemId) {

        Items item= itemsRepository.findById(ItemId);
        byte[] image = item.getImage();
        item.setImageString(Base64.encodeBase64String(image));
        model.addAttribute("item", item);

        return "selected-items";
    }
}