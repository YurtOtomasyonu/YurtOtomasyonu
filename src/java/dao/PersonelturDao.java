package dao;

import entity.Personeltur;
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


public class PersonelturDao {

    private DBConnection db;
    private Connection c;

    public List<Personeltur> findAll() {
        List<Personeltur> ptList = new ArrayList<>();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from personel_tur");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Personeltur tmp = new Personeltur();
                tmp.setTur_id(rs.getLong("tur_id"));
                tmp.setTur_ismi(rs.getString("tur_ismi"));

                ptList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ptList;
    }

    public Personeltur find(Long id) {
        Personeltur pt = null;
        try {
            PreparedStatement st = this.getC().prepareStatement("select * from personel_tur where tur_id=?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();

            pt = new Personeltur();
            pt.setTur_id(rs.getLong("tur_id"));
            pt.setTur_ismi(rs.getString("tur_ismi"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(PersonelturDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pt;
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
