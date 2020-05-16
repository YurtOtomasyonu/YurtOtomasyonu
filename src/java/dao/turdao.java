package dao;

import entity.duyurlar_turu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class turdao {

    private DBConnection connector;
    private Connection connection;

    public List<duyurlar_turu> findAll() {
        List<duyurlar_turu> dList = new ArrayList<>();
        try {
            Statement st = this.getC().createStatement();
            ResultSet rs = st.executeQuery("select * from duyurlar_turu");

            while (rs.next()) {
                duyurlar_turu tmp = new duyurlar_turu();
                tmp.setTur_id(rs.getLong("tur_id"));
                tmp.setIsim(rs.getString("isim"));
                dList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return dList;
    }

    public duyurlar_turu find(Long id) {
        duyurlar_turu d = null;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from duyurlar_turu where tur_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            d = new duyurlar_turu();
            d.setTur_id(rs.getLong("tur_id"));
            d.setIsim(rs.getString("isim"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return d;
    }

    public DBConnection getDb() {
        if(this.connector==null){
            this.connector = new DBConnection();
        }
        return connector;
    }

    public Connection getC() {
        if (this.connection == null) {
            this.connection = this.getDb().connect();
        }
        return connection;
    }
   
}
