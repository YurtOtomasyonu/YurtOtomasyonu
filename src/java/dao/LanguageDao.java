
package dao;

import entity.Language;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class LanguageDao extends BaseDao {

 
    //16
    public List<Language> findAll(){
        List<Language> llist=new ArrayList<>();
       try{
          
           Statement st = this.getConnection().createStatement();
           ResultSet rs = st.executeQuery("select * from language");
           while(rs.next()){
               Language tmp = new Language();
               tmp.setLanguage_id(rs.getLong("language_id"));
               tmp.setName(rs.getString("name"));
               
               llist.add(tmp);
               
           }
       } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return llist;
    }
   
//14
    public Language find(Long id) {
        Language l = null;
        try {
             PreparedStatement pst = this.getConnection().prepareStatement("select * from language where language_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            
           
           rs.next();
            //eğer veri bulurs yeni bir dögü icinde nesne olusutr
            l=new Language();
            l.setLanguage_id(rs.getLong("language_id"));
            l.setName(rs.getString("name"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return l;

    }

}
