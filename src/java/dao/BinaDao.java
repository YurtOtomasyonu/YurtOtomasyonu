package dao;

import entity.Bina;
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

public class BinaDao {

    private DBConnection db;
    private Connection c;

    public List<Bina> findAll() {
        List<Bina> binaList = new ArrayList<>();
        //Connector connector = new Connector();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from bina");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Bina tmp = new Bina(rs.getLong("bina_id"), rs.getString("oda_sayisi"),rs.getString("adres"));
                binaList.add(tmp);
            }
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(BinaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return binaList;
    }
    public Bina find(Long id){
        Bina bb = null;
        try{
            PreparedStatement st = this.getC().prepareStatement("select * from bina where bina_id=?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            
            bb = new Bina();
            bb.setBina_id(rs.getLong("bina_id"));
            bb.setOda_sayisi(rs.getString("oda_sayisi"));
            bb.setAdres(rs.getString("adres"));
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(PersonelturDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bb;
    }

    public void insert(Bina bina) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("insert into bina (oda_sayisi,adres) values (?,?)");
            pst.setString(1, bina.getOda_sayisi());
            pst.setString(2, bina.getAdres());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(BinaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Bina bina) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from bina where bina_id=?");
            pst.setLong(1, bina.getBina_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(BinaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Bina bina) {

        try {
            PreparedStatement pst = this.getC().prepareStatement("update bina set oda_sayisi=?, adres=? where bina_id=?");
            pst.setString(1, bina.getOda_sayisi());
            pst.setString(2, bina.getAdres());
            pst.setLong(3, bina.getBina_id());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(BinaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DBConnection getDb() {
        if (this.db == null) {
            this.db = new DBConnection();
        }
        return db;
    }

    public Connection getC() {
        if (this.c == null) {
            this.c = this.getDb().connect();
        }
        return c;
    }

}
