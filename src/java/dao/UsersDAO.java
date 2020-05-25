package dao;

import entity.User;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import login_logout.DBCon;

public class UsersDAO implements Serializable {

    public User validate(String user) {
        Connection con;
        PreparedStatement ps;

        User user2 = null;

        try {
            con = DBCon.getConnection();
            ps = con.prepareStatement("select * from user  where  uname='" + user + "'");

            ResultSet rs = ps.executeQuery();
            rs.next();
            user2 = new User();

            user2.setUsername(rs.getString("uname"));
            user2.setPassword(rs.getString("password"));
            user2.setUturu(rs.getString("userTuru"));
            user2.setC(rs.getLong("c"));
            user2.setR(rs.getLong("r"));
            user2.setU(rs.getLong("u"));
            user2.setD(rs.getLong("d"));

        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        }
        return user2;
    }

    public static void insert(String uname, String password, String userTuru) {
        DBCon.getConnection();

        try {
            Statement sts = DBCon.getConnection().createStatement();
            String sql = "INSERT INTO user(uname,password,userTuru,c,r,u,d) VALUES('" + uname + "','" + password + "','" + userTuru + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "')";
            sts.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
