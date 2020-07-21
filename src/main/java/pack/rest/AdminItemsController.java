package pack.rest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pack.domain.Categories;
import pack.domain.Items;
import pack.repositories.CategoriesRepository;
import pack.repositories.ItemsRepository;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;


@Controller
public class AdminItemsController {

    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private ItemsRepository itemsRepository;



    @GetMapping(value = "/adm/main/items/{ItemId}")
    public String adminItemPage(Model model,@PathVariable long ItemId) {

        Items item= itemsRepository.findById(ItemId);
        byte[] image = item.getImage();
        item.setImageString(Base64.encodeBase64String(image));
        model.addAttribute("item", item);

        return "admin-selected-items";
    }

    @GetMapping(value = {"/adm/items/add"})
    public String adminAddItemPage(Model model) {

        Items item = new Items();
        model.addAttribute("item", item);
        List<Categories> categories = categoriesRepository.findAll();
        model.addAttribute("categories",categories);

        return "add-item";
    }

    @PostMapping(value = {"/adm/items/add"})
    public String adminAddItem(Model model,RedirectAttributes redirectAttributes, @ModelAttribute("item") Items item
            ,@RequestParam("img") MultipartFile file, @RequestParam("categoryId") long categoryId) throws IOException {

        Categories ctg =categoriesRepository.findById(categoryId);
        item.setImage(file.getBytes());
        item.setCategory(ctg);
        itemsRepository.save(item);
        redirectAttributes.addAttribute("ctgId", ctg.getId());
        ctg.addItem(item);
         categoriesRepository.save(ctg);

        return "redirect:/adm/main/categories/{ctgId}";
    }

    @RequestMapping(value = "/adm/items/delete/{itemId}")
    public String adminDeleteItem(Model model,RedirectAttributes redirectAttributes, @PathVariable long itemId) {

        Items item = itemsRepository.findById(itemId);
        long ctgId = item.getCategory().getId();
        redirectAttributes.addAttribute("ctgId", ctgId);
        model.addAttribute("redirectId", ctgId);
        itemsRepository.deleteById(itemId);

        return "redirect:/adm/main/categories/{ctgId}";
    }


    @GetMapping(value = {"/adm/items/change/{itemId}"})
    public String adminChangeItemPage(Model model, @PathVariable long itemId) {

       Items item =itemsRepository.findById(itemId);
        List<Categories> ctgs  =categoriesRepository.findAll();
        model.addAttribute("categories", ctgs);
        model.addAttribute("item", item);

        return "change-item";
    }



    @PostMapping(value = {"/adm/items/change/{itemId}"})
    public String adminChangeItem(Model model,RedirectAttributes redirectAttributes, @PathVariable long itemId,
                                      @ModelAttribute("item") Items item,@RequestParam("img") MultipartFile file) throws IOException {

        Items helpitm =itemsRepository.findById(itemId);
        Categories ctg = helpitm.getCategory();
        item.setId(itemId);
        item.setCategory(ctg);
        if (!file.isEmpty()) {
            item.setImage(file.getBytes());
        } else {
            item.setImage(helpitm.getImage());
        }
        itemsRepository.save(item);
        redirectAttributes.addAttribute("ctgId", ctg.getId());

        return "redirect:/adm/main/categories/{ctgId}";
    }
}
