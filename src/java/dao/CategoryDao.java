package dao;

import entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class CategoryDao extends BaseDao {

    public List<Category> findAll(int page, int pageSize, String searchTerm) {

        List<Category> categorylist = new ArrayList();

        int start = (page - 1) * pageSize;
        try {

            String query = "select * from category";

            if (searchTerm != null) {
                query += " where name like ? ";
            }

            query += " order by category_id asc limit ? offset ?";
            PreparedStatement st = this.getConnection().prepareStatement(query);

            if (searchTerm != null) {

                st.setString(1, "%" + searchTerm + "%");
                st.setInt(2, pageSize);
                st.setInt(3, (page - 1) * pageSize);
            } else {
                st.setInt(1, pageSize);
                st.setInt(2, (page - 1) * pageSize);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category t = new Category();
                t.setCategory_id(rs.getLong("category_id"));
                t.setName(rs.getString("name"));
                t.setLast_update(rs.getDate("last_update"));
                categorylist.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return categorylist;
    }

    public void insert(Category category) {

        try {
            Statement st = this.getConnection().createStatement();
            st.executeUpdate("insert into category (name)  values ('" + category.getName() + "')");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void remove(Category cat) {

        try {
            /*Statement st = this.getConnection().createStatement();
            st.executeUpdate("delete from category where category_id ="+cat.getCategory_id());*/
 /*  PreparedStatement pst = this.getConnection().prepareStatement("delete from category where category_id=?");
            pst.setLong(1, cat.getCategory_id());
            pst.executeUpdate();*/
            PreparedStatement pst = this.getConnection().prepareStatement("delete from category where category_id=?");
            pst.setLong(1, cat.getCategory_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Category category) {

        try {
            Statement st = this.getConnection().createStatement();
            st.executeUpdate("update category set name='" + category.getName() + "' where category_id= " + category.getCategory_id());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Category> getKutupCategoris(long kitab_id) {
        List<Category> kutupCategories = new ArrayList<>();

        try {
            Statement st = this.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from kutup_category where kitab_id=" + kitab_id);
            // n tane sonuc donme ihtmali oldugu icin 

            while (rs.next()) {
                kutupCategories.add(this.find(rs.getLong("category_id")));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return kutupCategories;

    }

    public Category find(Long id) {
        Category c = null;
        try {
            Statement st = this.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from category where category_id=" + id);
            rs.next();

            c = new Category();
            c.setCategory_id(rs.getLong("category_id"));
            c.setName(rs.getString("name"));
            c.setLast_update(rs.getDate("last_update"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return c;

    }

//15
    public List<Category> getKutuphaneCategories(long kitab_id) {
        List<Category> kutuphaneCategories = new ArrayList<>();

        try {
            Statement st = this.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from kutup_category where kitab_id=" + kitab_id);

            while (rs.next()) {
                kutuphaneCategories.add(this.find(rs.getLong("category_id")));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return kutuphaneCategories;
    }

    public int count(String searchTerm) {
        int count = 0;

        try {
            /*   PreparedStatement pst = this.getConnection().prepareStatement("select count(category_id) as cat_count from category ");
           
            ResultSet rs = pst.executeQuery();
            rs.next() ;
            count = rs.getInt("cat_count") ;*/
            String query = "select count(category_id) as aa from category";
            if (searchTerm != null) {
                query += " where name like ? ";
            }
            PreparedStatement st = this.getConnection().prepareStatement(query);

            if (searchTerm != null) {
                st.setString(1, "%" + searchTerm + "%");
            }
            ResultSet rs = st.executeQuery();

            rs.next();
            count = rs.getInt("aa");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }
}
