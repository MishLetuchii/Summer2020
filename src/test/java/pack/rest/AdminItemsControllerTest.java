package pack.rest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pack.domain.Categories;
import pack.domain.Items;
import pack.repositories.CategoriesRepository;
import pack.repositories.ItemsRepository;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AdminItemsControllerTest {

    private MockMvc mvc;
    private CategoriesRepository categoriesRepository;
    private ItemsRepository itemsRepository;

    @Autowired
    public void setMvc(MockMvc mvc) {
        this.mvc = mvc;
    }

    @Autowired
    public void setCategoriesRepository(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Autowired
    public void setItemsRepository(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Test
    @Transactional
    public void adminAddItemPage() throws Exception {

        Categories ctg = new Categories();
        ctg.setName("TestCategoryName");
        ctg.setDescription("TestCategoryDescription");
        ctg.setImage(null);
        ctg.setItems(null);
        categoriesRepository.save(ctg);

        Items item = new Items();
        item.setName("TestItemName");
        item.setDescription("TestItemDescription");
        item.setCategory(ctg);
        item.setImage(null);
        item.setArticul(0);
        item.setPrice(0);
        item.setCount(0);
        itemsRepository.save(item);



        MockMultipartFile img = new MockMultipartFile("img",
                "img", "text/plain", item.getName().getBytes());

        mvc.perform(MockMvcRequestBuilders.multipart("/adm/items/add", item.getId()).file(img).param("name", item.getName()).param("description", item.getDescription()))
                .andExpect(model().size(0))
                .andExpect(status().is3xxRedirection());
    }



}
