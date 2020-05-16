
package dao;

import entity.Category;
import entity.Kutuphane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;


public class KutuphaneDao {

    private DBConnection connector;
    private Connection connection;
    //instans dao 
    private LanguageDao languageDao;
    //instanse list many to many
    private CategoryDao categoryDao;

    public void create(Kutuphane kutuphane) {
        try {
            // Statement st = this.getConnection().createStatement();
            PreparedStatement pst = this.getConnection().prepareStatement("insert into kutuphane (kitab_adi , description , year ,language_id) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            // st.executeUpdate("insert into kutuphane (kitab_adi , description , year,language_id) values ('" + kutuphane.getKitab_adi() + "','" + kutuphane.getDescription() + "', '" + kutuphane.getYear() + "','" + selectedLanguage + "')", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, kutuphane.getKitab_adi());
            pst.setString(2, kutuphane.getDescription());
            pst.setInt(3, kutuphane.getYear());
            pst.setLong(4, kutuphane.getLanguage().getLanguage_id());

            pst.executeUpdate();
            Long kitab_id = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                kitab_id = gk.getLong(1);
            }

            for (Category c : kutuphane.getKutuphaneCategories()) {
                //Statement st2 = this.getConnection().createStatement();
                pst = this.getConnection().prepareStatement("insert into kutup_category (kitab_id , category_id) values(?,?)");
                //st2.executeUpdate("insert into kutup_category (kitab_id , category_id) values (" + kitab_id + "," + l + ")");
                pst.setLong(1, kitab_id);
                pst.setLong(2, c.getCategory_id());
                pst.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void edit(Kutuphane kutuphane) {

        try {
            // Statement st = this.getConnection().createStatement();
            PreparedStatement pst = this.getConnection().prepareStatement("update kutuphane set kitab_adi=? , description=? , year=? , language_id=?  values (?,?,?,?) where kitab_id=? ");
            // st.executeUpdate("insert into kutuphane (kitab_adi , description , year,language_id) values ('" + kutuphane.getKitab_adi() + "','" + kutuphane.getDescription() + "', '" + kutuphane.getYear() + "','" + selectedLanguage + "')", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, kutuphane.getKitab_adi());
            pst.setString(2, kutuphane.getDescription());
            pst.setInt(3, kutuphane.getYear());
            pst.setLong(4, kutuphane.getLanguage().getLanguage_id());
            pst.setLong(5, kutuphane.getKitab_id());

            pst.executeUpdate();

            pst = this.getConnection().prepareStatement("delete from kutup_category where kitab_id=? ");
            pst.setLong(1, kutuphane.getKitab_id());
            
            pst.executeUpdate();

            for (Category c : kutuphane.getKutuphaneCategories()) {
                //Statement st2 = this.getConnection().createStatement();
                pst = this.getConnection().prepareStatement("insert into kutup_category (kitab_id , category_id) values (?,?) ");
                //st2.executeUpdate("insert into kutup_category (kitab_id , category_id) values (" + kitab_id + "," + l + ")");
                pst.setLong(1, kutuphane.getKitab_id());
                pst.setLong(2, c.getCategory_id());
                pst.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void remove(Kutuphane kutuphane) {

        try {
            //ilikleri kaldırmak icin
            PreparedStatement pst = this.getConnection().prepareStatement("delete from kutup_category where kitab_id=?");
            pst.setLong(1, kutuphane.getKitab_id());
            pst.executeUpdate();

            pst = this.getConnection().prepareStatement("delete from kutuphane where kitab_id=?");
            pst.setLong(1, kutuphane.getKitab_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Kutuphane> findAll() {
        List<Kutuphane> kutuphaneList = new ArrayList<>();

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from kutuphane");
            // Statement st = this.getConnection().createStatement();
            // ResultSet rs = st.executeQuery("select * from kutuphane");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Kutuphane tmp = new Kutuphane();
                tmp.setKitab_id(rs.getLong("kitab_id"));
                tmp.setKitab_adi(rs.getString("kitab_adi"));
                tmp.setDescription(rs.getString("description"));
                tmp.setYear(rs.getInt("year"));
                //many to one 
                tmp.setLanguage(this.getLanguageDao().find(rs.getLong("language_id")));
                //many to many 
                tmp.setKutuphaneCategories(this.getCategoryDao().getKutuphaneCategories(tmp.getKitab_id()));
                kutuphaneList.add(tmp);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return kutuphaneList;
    }

    public LanguageDao getLanguageDao() {
        if (this.languageDao == null) {
            this.languageDao = new LanguageDao();
        }
        return languageDao;
    }

    public void setLanguageDao(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }

    public DBConnection getConnector() {
        if (this.connector == null) {
            this.connector = new DBConnection();
        }

        return connector;
    }

    public Connection getConnection() {
        if (this.connection == null) {
            this.connection = this.getConnector().connect();
        }
        return connection;
    }

    public CategoryDao getCategoryDao() {
        if (this.categoryDao == null) {
            this.categoryDao = new CategoryDao();
        }
        return categoryDao;
    }

}
