import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatDAOImpl implements CatDAO{
    public List<Cat> getAllCats() {
        List<Cat> cats = new ArrayList<Cat>();
        try(Connection conn = ConnectionToDB.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM testdb.cats");
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()){
                Cat cat = new Cat();
                cat.setId(resultSet.getInt("id"));
                cat.setName(resultSet.getString("name"));
                cat.setAge(resultSet.getInt("age"));
                cat.setColor(resultSet.getString("color"));
                cats.add(cat);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cats;
    }

    public Cat getCat(int id) throws CatNotFoundException {
        Cat cat = new Cat();
        try(Connection conn = ConnectionToDB.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM testdb.cats WHERE id=?")){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    cat.setId(resultSet.getInt("id"));
                    cat.setName(resultSet.getString("name"));
                    cat.setAge(resultSet.getInt("age"));
                    cat.setColor(resultSet.getString("color"));
                } else{
                    throw new CatNotFoundException();
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cat;
    }

    public boolean addCat(Cat cat){
        Cat isExists = new Cat();
        try {
            isExists = getCat(cat.getId());
        } catch (CatNotFoundException e) {
            try(Connection conn = ConnectionToDB.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO testdb.cats (id, name, age, color) VALUES (?,?,?,?)")){
                preparedStatement.setInt(1, cat.getId());
                preparedStatement.setString(2,cat.getName());
                preparedStatement.setInt(3, cat.getAge());
                preparedStatement.setString(4, cat.getColor());
                if(preparedStatement.executeUpdate()==1){
                    System.out.println("The "+ cat.getName()+" was added");
                    return true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    public boolean changeCat(int id, Cat cat) {
        Cat isExists = new Cat();
        try {
            isExists = getCat(id);
        } catch (CatNotFoundException e) {
            System.out.println("f");
            return false;
        }
        try (Connection conn = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("UPDATE testdb.cats SET name=?, age=?, color=? WHERE id=?")) {
            preparedStatement.setString(1, cat.getName());
            preparedStatement.setInt(2, cat.getAge());
            preparedStatement.setString(3, cat.getColor());
            preparedStatement.setInt(4, cat.getId());
            if (preparedStatement.executeUpdate() == 1) {
                System.out.println("The " + cat.getName() + " was changed");
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("s");
        return false;
    }

    public boolean deleteCat(int id) {
        Cat isExists = new Cat();
        try {
            isExists = getCat(id);
        } catch (CatNotFoundException e) {
            return false;
        }
        try(Connection conn=ConnectionToDB.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM testdb.cats WHERE id=?")){
            preparedStatement.setInt(1, id);
            if(preparedStatement.executeUpdate()==1){
                System.out.println("The " + isExists.getName() + " was deleted");
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
