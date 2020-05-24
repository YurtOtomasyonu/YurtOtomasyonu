/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.duyuru;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import util.DBConnection;


public class duyuruDao extends BaseDao {

   

    private turdao turdao;

    public List<duyuru> findAll() {
        List<duyuru> dList = new ArrayList();
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from duyurular");
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                duyuru tmp = new duyuru();
                tmp.setDuyuru_id(rs.getLong("duyuru_id"));
                tmp.setBilgi(rs.getString("bilgi"));


                rs.getInt("duyurlar_turu");
                tmp.setDuyurlar_turu(this.getTurdao().find(rs.getLong("duyurlar_turu")));
                dList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return dList;
    }

    public void create(duyuru dyr) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into duyurular(bilgi,duyurlar_turu) values(?,?)");
            pst.setString(1, dyr.getBilgi());
            pst.setLong(2, dyr.getDuyurlar_turu().getTur_id());

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void edit(duyuru dyr) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update duyurular set bilgi=?,duyurlar_turu=? where duyuru_id=?");
            
            pst.setLong(3, dyr.getDuyuru_id());
            pst.setString(1, dyr.getBilgi());
            pst.setLong(2, dyr.getDuyurlar_turu().getTur_id());

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void remove(duyuru dyr) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from duyurular where duyuru_id=?");
            pst.setLong(1, dyr.getDuyuru_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public turdao getTurdao() {
        if (this.turdao == null) {
            this.turdao = new turdao();
        }
        return turdao;
    }

}