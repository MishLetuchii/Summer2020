package pack.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pack.repositories.CategoriesRepository;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import javax.transaction.Transactional;
import pack.domain.Categories;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminCategoriesController.class)
@AutoConfigureMockMvc(addFilters = false)
@OverrideAutoConfiguration(enabled = true)
public class AdminCategoriesControllerTest {


    private MockMvc mvc;
    private CategoriesRepository categoriesRepository;

    @Autowired
    public void setMvc(MockMvc mvc) {
        this.mvc = mvc;
    }

    @Autowired
    public void setCategoriesRepository(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }



    @Test
    public void main() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/adm/main"))
                .andExpect(model().size(1))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void adminDeleteCategoryTest() throws Exception {
        Categories ctg = new Categories();
        ctg.setName("TestCategoryName");
        ctg.setDescription("TestCategoryDescription");
        ctg.setImage(null);
        ctg.setItems(null);
        categoriesRepository.save(ctg);

        mvc.perform(MockMvcRequestBuilders.post("/adm/categories/delete/{categoryId}", ctg.getId()))
                .andExpect(redirectedUrl("/adm/main"))
                .andExpect(model().size(0))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @Transactional
    public void adminAddCategoryTest() throws Exception {
        Categories ctg = new Categories();
        ctg.setName("TestCategoryName");
        ctg.setDescription("TestCategoryDescription");
        ctg.setImage(null);
        ctg.setItems(null);
        categoriesRepository.save(ctg);

        MockMultipartFile img = new MockMultipartFile("img",
                "img", "text/plain", ctg.getName().getBytes());

        mvc.perform(MockMvcRequestBuilders.multipart("/adm/categories/add").file(img))
                .andExpect(redirectedUrl("/adm/main"))
                .andExpect(model().size(0))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @Transactional
    public void adminChangeCategoryTest() throws Exception {
        Categories ctg = new Categories();
        ctg.setName("TestCategoryName");
        ctg.setDescription("TestCategoryDescription");
        ctg.setImage(null);
        ctg.setItems(null);
        categoriesRepository.save(ctg);

        MockMultipartFile img = new MockMultipartFile("img",
                "img", "text/plain", ctg.getName().getBytes());

        mvc.perform(MockMvcRequestBuilders.multipart("/adm/categories/change/{categoryId}", ctg.getId()).file(img).param("name", ctg.getName()).param("description", ctg.getDescription()))
                .andExpect(redirectedUrl("/adm/main"))
                .andExpect(model().size(0))
                .andExpect(status().is3xxRedirection());
    }

}
