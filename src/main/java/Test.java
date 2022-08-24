import java.util.List;

public class Test {
    public static void main(String[] args) {
       CatDAOImpl catDAO = new CatDAOImpl();
       Cat cat = new Cat(1, "Barsik", 2, "Black");
       Cat cat2 = new Cat(2, "Tom", 4, "White");
       catDAO.addCat(cat);//create
       catDAO.addCat(cat2);

        List<Cat> cats = catDAO.getAllCats();//read
        System.out.println(cats);


        cat.setColor("Brown");
        catDAO.changeCat(1,cat);//update


        try {
            Cat cat3 = catDAO.getCat(1);//read
            System.out.println(cat3);
        } catch (CatNotFoundException e) {
            e.printStackTrace();
        }

        catDAO.deleteCat(1);//delete
        catDAO.deleteCat(2);


    }
}
