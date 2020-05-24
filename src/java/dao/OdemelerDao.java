package dao;

import entity.Odemeler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBConnection;


public class OdemelerDao  extends BaseDao{

 

    public List<Odemeler> findAll() {
        List<Odemeler> odemelerList = new ArrayList<>();
        //Connector connector = new Connector();
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from odemeler");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Odemeler tmp = new Odemeler(rs.getLong("odeme_id"), rs.getString("ogrenciAdi"),rs.getInt("ucret"));
                odemelerList.add(tmp);
            }
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(OdemelerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return odemelerList;
    }
    public Odemeler find(Long id){
        Odemeler bb = null;
        try{
            PreparedStatement st = this.getConnection().prepareStatement("select * from odemeler where odeme_id=?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            
            bb = new Odemeler();
            bb.setOdeme_id(rs.getLong("odeme_id"));
            bb.setOgrenciAdi(rs.getString("ogrenciAdi"));
            bb.setUcret(rs.getInt("ucret"));
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(PersonelturDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bb;
    }

    public void insert(Odemeler odemeler) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into odemeler (ogrenciAdi,ucret) values (?,?)");
            pst.setString(1, odemeler.getOgrenciAdi());
            pst.setInt(2, odemeler.getUcret());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(OdemelerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Odemeler odemeler) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from odemeler where odeme_id=?");
            pst.setLong(1, odemeler.getOdeme_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(OdemelerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Odemeler odemeler) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update odemeler set ogrenciAdi=?, ucret=? where odeme_id=?");
            pst.setString(1, odemeler.getOgrenciAdi());
            pst.setInt(2, odemeler.getUcret());
            pst.setLong(3, odemeler.getOdeme_id());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(OdemelerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
