package dao;

import entity.Personel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBConnection;


public class PersonelDao extends BaseDao {
    

    
    private PersonelturDao personelturDao;
    
    public List<Personel> findAll() {
        List<Personel> personelList = new ArrayList<>();
        
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("select * from personel");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Personel tmp = new Personel();
                tmp.setPersonel_id(rs.getLong("personel_id"));
                tmp.setIsim(rs.getString("isim"));
                tmp.setSoyad(rs.getString("soyad"));
                tmp.setTc(rs.getString("tc"));
                
                tmp.setPersoneltur(this.getPersonelturDao().find(rs.getLong("tur_id")));
                personelList.add(tmp);
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return personelList;
    }
     public void remove(Personel personel) {
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("delete from personel where personel_id=?");
            pst.setLong(1, personel.getPersonel_id());
            pst.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    
    public void create(Personel personel) {
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("insert into personel (isim,soyad,tc,tur_id) values (?,?,?,?)");
            pst.setString(1, personel.getIsim());
            pst.setString(2, personel.getSoyad());
            pst.setString(3, personel.getTc());
            pst.setLong(4, personel.getPersoneltur().getTur_id());
            
            pst.executeUpdate();
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
       
    }
    public void edit(Personel personel) {
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("update personel set isim=?, soyad=?, tc=?, tur_id=? where personel_id=?");
            pst.setString(1, personel.getIsim());
            pst.setString(2, personel.getSoyad());
            pst.setString(3, personel.getTc());
            pst.setLong(4, personel.getPersoneltur().getTur_id());
            pst.setLong(5, personel.getPersonel_id());
            
            pst.executeUpdate();
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public PersonelturDao getPersonelturDao() {
        if(this.personelturDao == null){
            this.personelturDao = new PersonelturDao();
        }
        return personelturDao;
    }
    

}
