import java.util.List;

public interface CatDAO {
    List<Cat> getAllCats();
    Cat getCat(int id) throws CatNotFoundException;
    boolean addCat(Cat cat);
    boolean changeCat(int id, Cat cat);
    boolean deleteCat(int id);
}
