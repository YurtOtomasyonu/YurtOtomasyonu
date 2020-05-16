package dao;

import entity.Kantin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import util.DBConnection;


public class KantinDao {
    
    private DBConnection db;
    private Connection c;
    
    private KantinturDao kantinturDao;
    
    public List<Kantin> findAll() {
        List<Kantin> kantinList = new ArrayList<>();
        
        try{
            PreparedStatement pst = this.getC().prepareStatement("select * from kantin");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Kantin tmp = new Kantin();
                tmp.setMalzeme_id(rs.getLong("malzeme_id"));
                tmp.setIsim(rs.getString("isim"));
                
                tmp.setKantintur(this.getKantinturDao().find(rs.getLong("tur_id")));
                kantinList.add(tmp);
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return kantinList;
    }
     public void remove(Kantin kantin) {
        try{
            PreparedStatement pst = this.getC().prepareStatement("delete from kantin where malzeme_id=?");
            pst.setLong(1, kantin.getMalzeme_id());
            pst.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    
    public void create(Kantin kantin) {
        try{
            PreparedStatement pst = this.getC().prepareStatement("insert into kantin (isim,tur_id) values (?,?)");
            pst.setString(1, kantin.getIsim());
            pst.setLong(2, kantin.getKantintur().getTur_id());
            
            pst.executeUpdate();
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
       
    }
    public void edit(Kantin kantin) {
        try{
            PreparedStatement pst = this.getC().prepareStatement("update kantin set isim=?, tur_id=? where malzeme_id=?");
            pst.setString(1, kantin.getIsim());
            pst.setLong(2, kantin.getKantintur().getTur_id());
            pst.setLong(3, kantin.getMalzeme_id());
            
            pst.executeUpdate();
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public KantinturDao getKantinturDao() {
        if(this.kantinturDao == null){
            this.kantinturDao = new KantinturDao();
        }
        return kantinturDao;
    }
    
    public DBConnection getDb() {
        if(this.db==null){
            this.db = new DBConnection();
        }
        return db;
    }

    public Connection getC() {
        if(this.c==null){
            this.c = this.getDb().connect();
        }
        return c;
    }
}
