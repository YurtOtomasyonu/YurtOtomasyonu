package dao;


import entity.Ogrenci;
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

public class OgrenciDao {
    
    private DBConnection db;
    private Connection c;
    
    private OdalarDao odalarDao;
    
    public List<Ogrenci> findAll() {
        List<Ogrenci> ogrenciList = new ArrayList<>();
        
        try{
            PreparedStatement pst = this.getC().prepareStatement("select * from ogrenci");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Ogrenci tmp = new Ogrenci();
                tmp.setOgr_id(rs.getLong("ogr_id"));
                tmp.setAdi(rs.getString("adi"));
                tmp.setSoyadi(rs.getString("soyadi"));
               
                
                tmp.setOdalar(this.getOdalarDao().find(rs.getLong("oda_id")));
                //tmp.setOdalar(this.getOdalarDao().getBinaDao().find(rs.getLong("bina_id")));
                ogrenciList.add(tmp);
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return ogrenciList;
    }
     public void remove(Ogrenci ogrenci) {
        try{
            PreparedStatement pst = this.getC().prepareStatement("delete from ogrenci where ogr_id=?");
            pst.setLong(1, ogrenci.getOgr_id());
            pst.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    
    public void create(Ogrenci ogrenci) {
        try{
            PreparedStatement pst = this.getC().prepareStatement("insert into ogrenci (adi,soyadi,oda_id) values (?,?,?)");
            pst.setString(1, ogrenci.getAdi());
            pst.setString(2, ogrenci.getSoyadi());
            pst.setLong(3, ogrenci.getOdalar().getOda_id());
            
            pst.executeUpdate();
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void edit(Ogrenci ogrenci) {
        try{
            PreparedStatement pst = this.getC().prepareStatement("update ogrenci set adi=?,soyadi=?,oda_id=? where ogr_id=?");
            pst.setString(1, ogrenci.getAdi());
            pst.setString(2, ogrenci.getSoyadi());
            pst.setLong(3, ogrenci.getOdalar().getOda_id());
            pst.setLong(4, ogrenci.getOgr_id());
            
            pst.executeUpdate();
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public OdalarDao getOdalarDao() {
        if(this.odalarDao == null){
            this.odalarDao = new OdalarDao();
        }
        return odalarDao;
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
