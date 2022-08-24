import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;

class CatDAOTest {
    CatDAOImpl catDAO = new CatDAOImpl();


    @Tag("Test to use method getAllCats and read two cats")
    @Test
    void getAllCats(){
        Cat cat = new Cat(1, "Barsik", 2, "Black");
        catDAO.addCat(cat);
        Cat cat2 = new Cat(2, "Tom", 4, "White");
        catDAO.addCat(cat2);
        Assert.assertNotNull(catDAO.getAllCats());
        catDAO.deleteCat(1);
        catDAO.deleteCat(2);
    }



    @Tag("Test to delete the cat")
    @Test
    void deleteCat(){
        Cat cat = new Cat(1, "Barsik", 2, "Black");
        catDAO.addCat(cat);
        Assert.assertTrue(catDAO.deleteCat(1));
    }
}