package pack.rest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import pack.domain.Categories;
import pack.domain.Items;
import pack.repositories.CategoriesRepository;
import pack.repositories.ItemsRepository;
import org.springframework.ui.Model;
import pack.repositories.UsersRepository;

import java.io.IOException;
import java.util.List;


@Controller
public class AdminCategoriesController {

    private CategoriesRepository categoriesRepository;
    private ItemsRepository itemsRepository;

    @Autowired
    public AdminCategoriesController(CategoriesRepository categoriesRepository, ItemsRepository itemsRepository) {
        this.categoriesRepository = categoriesRepository;
        this.itemsRepository = itemsRepository;
    }

    @GetMapping(value = "/adm/main")
    public String main(Model model) {

        List<Categories> categories = categoriesRepository.findAll();

        for (Categories category : categories) {
            byte[] image = category.getImage();
            category.setImageString(Base64.encodeBase64String(image));
        }
        model.addAttribute("categories", categories);

        return "admin-main";
    }

    @GetMapping(value = "/adm/main/categories/{CtgId}")
    public String adminCategoriesPage(Model model, @PathVariable long CtgId) {

        Categories categ = categoriesRepository.findById(CtgId);
        List<Items> items= itemsRepository.findByCategory(categ);
        for (Items item : items) {
            byte[] image = item.getImage();
            item.setImageString(Base64.encodeBase64String(image));
        }
        model.addAttribute("items", items);
        model.addAttribute("category",categ);

        return "admin-main-ctg";
    }

    @GetMapping(value = {"/adm/categories/add"})
    public String adminAddCategoryPage(Model model) {

        Categories ctg = new Categories();
        model.addAttribute("category", ctg);

        return "add-category";
    }

    @PostMapping(value = {"/adm/categories/add"})
    public String adminAddCategory(Model model, @ModelAttribute("category") Categories ctg
        ,@RequestParam("img") MultipartFile file) throws IOException {

        ctg.setImage(file.getBytes());
        categoriesRepository.save(ctg);

        return "redirect:/adm/main";
    }

    @RequestMapping(value = "/adm/categories/delete/{categoryId}")
    public String adminDeleteCategory(Model model, @PathVariable long categoryId) {

        categoriesRepository.deleteById(categoryId);

        return "redirect:/adm/main";
    }

    @GetMapping(value = {"/adm/categories/change/{categoryId}"})
    public String adminChangeCategoryPage(Model model, @PathVariable long categoryId) {

        Categories ctg =categoriesRepository.findById(categoryId);
        model.addAttribute("category", ctg);

        return "change-category";
    }

    @PostMapping(value = {"/adm/categories/change/{categoryId}"})
    public String adminChangeCategory(Model model, @PathVariable long categoryId,
      @ModelAttribute("category") Categories ctg,@RequestParam("img") MultipartFile file) throws IOException {

        Categories helpCtg =categoriesRepository.findById(categoryId);
        List<Items> items = itemsRepository.findByCategory(helpCtg);
        ctg.setId(categoryId);
        ctg.setItems(items);

        if (!file.isEmpty()) {
            ctg.setImage(file.getBytes());
        } else {
            ctg.setImage(helpCtg.getImage());
        }
        categoriesRepository.save(ctg);

        return "redirect:/adm/main";
    }
}
